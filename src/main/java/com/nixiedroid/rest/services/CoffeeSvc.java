package com.nixiedroid.rest.services;

import com.nixiedroid.rest.dto.CoffeeDTO;
import com.nixiedroid.rest.dto.UserDTOPlain;
import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.Coffee;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeSvc implements Validator {
    /**
     * Coffee repository accessor <br>
     * {@link org.springframework.beans.factory.annotation.Autowired}
     */
    private final CoffeeRepository coffeeRepo;
    private final UserRepository userRepo;


    public CoffeeSvc(CoffeeRepository coffeeRepo, UserRepository userRepo) {
        this.coffeeRepo = coffeeRepo;
        this.userRepo = userRepo;
    }

    public List<CoffeeDTO> findAll() {
        //return repo.findAll().stream().map(c -> convertToDTO(c)).toList();
        return coffeeRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<CoffeeDTO> findById(long id) {
        return coffeeRepo.findById(id).map(this::convertToDTO);
    }

    public boolean existsById(long id) {
        return coffeeRepo.existsById(id);
    }

    public void deleteById(long id) {
        coffeeRepo.deleteById(id);
    }

    public CoffeeDTO save(CoffeeDTO coffeeDTO) {
        Coffee coffee = convertToEntity(coffeeDTO);
        return convertToDTO(coffeeRepo.save(coffee));
    }

    private CoffeeDTO convertToDTO(Coffee coffee) {
        if (coffee == null) return null;
        return new CoffeeDTO(
                coffee.getName(),
                coffee.getHasMilk(),
                coffee.getLikedBy().stream()
                        .map(u -> new UserDTOPlain(u.getFirstName(), u.getLastName()))
                        .collect(Collectors.toSet())
        );
    }

    private Coffee convertToEntity(CoffeeDTO dto) {
        if (dto == null) return null;
        Coffee c = coffeeRepo.findDistinctByNameAndHasMilk(dto.name(), dto.hasMilk());
        if (c == null) {
            c = new Coffee();
            c.setName(dto.name());
            c.setHasMilk(dto.hasMilk());
            c.addLikedByAll(dto.likedBy().stream()
                    .map(u -> userRepo.findDistinctByFirstNameAndLastName(u.firstName(), u.lastName()))
                    .collect(Collectors.toSet()));
            return c;
        }
        return c;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CoffeeDTO.class);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
    }

}
