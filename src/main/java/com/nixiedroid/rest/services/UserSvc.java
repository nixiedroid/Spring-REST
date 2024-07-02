package com.nixiedroid.rest.services;

import com.nixiedroid.rest.dto.CoffeeDTOPlain;
import com.nixiedroid.rest.dto.UserDTO;
import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSvc  implements Validator {

    private final UserRepository userRepo;
    private final CoffeeRepository coffeeRepo;

    @Autowired
    public UserSvc(CoffeeRepository coffeeRepo,UserRepository userRepo) {
        this.coffeeRepo = coffeeRepo;
        this.userRepo = userRepo;
    }


    public List<UserDTO> findAll() {
        //return userRepo.findAll().stream().map(c -> convertToDTO(c)).toList();
        return userRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<UserDTO> findById(long id){
        return userRepo.findById(id).map(this::convertToDTO);
    }

    public boolean existsById(long id){
        return userRepo.existsById(id);
    }
    public void deleteById(long id){
        userRepo.deleteById(id);
    }

    public UserDTO save(UserDTO UserDTO) {
        User User = convertToEntity(UserDTO);
        return convertToDTO(userRepo.save(User));
    }
    
    

    private UserDTO convertToDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getFavCoffees().stream()
                        .map(c -> new CoffeeDTOPlain(c.getName(),c.getHasMilk()))
                        .collect(Collectors.toSet())
        );
    }

    private User convertToEntity(UserDTO dto) {
        if (dto == null) return null;
        User u = userRepo.findDistinctByFirstNameAndLastName(dto.firstName(),dto.lastName());
        if (u == null){
            u = new User();
            u.setFirstName(dto.firstName());
            u.setLastName(dto.lastName());
            u.addFavCoffeeAll(dto.favCoffees().stream()
                    .map(c -> coffeeRepo.findDistinctByNameAndHasMilk(c.name(),c.hasMilk()))
                    .collect(Collectors.toSet()));
            return u;
        }
            return u;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    @Override
    public void validate(@NonNull  Object target, @NonNull   Errors errors) {
    }
}
