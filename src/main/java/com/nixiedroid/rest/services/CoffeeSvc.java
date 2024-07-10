package com.nixiedroid.rest.services;

import com.nixiedroid.rest.dto.CoffeeDTO;
import com.nixiedroid.rest.dto.UserDTOPlain;
import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.Coffee;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public Optional<CoffeeDTO> findByUUID(UUID uuid){
        return coffeeRepo.findDistinctByUuid(uuid).map(this::convertToDTO);
    }

    public boolean existsByUUID(UUID uuid){
        return coffeeRepo.existsByUuid(uuid);
    }
    public void deleteByUUID(UUID uuid){

        coffeeRepo.deleteByUuid(uuid);
    }

    public CoffeeDTO save(CoffeeDTO coffeeDTO) {
        Coffee coffee = convertToEntity(coffeeDTO);
        return convertToDTO(coffeeRepo.save(coffee));
    }

    private CoffeeDTO convertToDTO(Coffee coffee) {
        if (coffee == null) return null;
        return new CoffeeDTO(
                coffee.getUuid(),
                coffee.getName(),
                coffee.getHasMilk(),
                coffee.getLikedBy().stream()
                        .map(u -> new UserDTOPlain(
                                u.getUuid(),
                                u.getFirstName(),
                                u.getLastName())
                        )
                        .collect(Collectors.toSet())
        );
    }

    private Coffee convertToEntity(CoffeeDTO dto) {
        if (dto == null) throw new NullPointerException();
        //Check if we should insert or update coffee
        Optional<Coffee> coffeeFromDB = Optional.empty();
        if (dto.uuid() != null) {
            coffeeFromDB = coffeeRepo.findDistinctByUuid(dto.uuid());
        }
        Coffee c;
        if (coffeeFromDB.isEmpty()) { //Insert sequence
            c = new Coffee();
            c.setUuid(UUID.randomUUID()); //Generate Random UUID
            c.setName(dto.name());
            c.setHasMilk(dto.hasMilk());
            c.addLikedByAll(dto.likedBy().stream()
                    .map(u -> userRepo.findDistinctByUuid(u.uuid()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
            return c;
        } else { //Update Sequence
            c = coffeeFromDB.get();
            c.setName(dto.name());
            c.setHasMilk(dto.hasMilk());
            c.addLikedByAll(dto.likedBy().stream()
                    .map(u -> userRepo.findDistinctByUuid(u.uuid()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
        }
        return coffeeFromDB.get();
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CoffeeDTO.class);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
    }

}
