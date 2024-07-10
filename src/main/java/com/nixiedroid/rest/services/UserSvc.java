package com.nixiedroid.rest.services;

import com.nixiedroid.rest.dto.CoffeeDTOPlain;
import com.nixiedroid.rest.dto.UserDTO;
import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<UserDTO> findByUUID(UUID uuid){
        return userRepo.findDistinctByUuid(uuid).map(this::convertToDTO);
    }

    public boolean existsByUUID(UUID uuid){
        return userRepo.existsByUuid(uuid);
    }
    public void deleteByUUID(UUID uuid){
        userRepo.deleteByUuid(uuid);
    }

    public UserDTO save(UserDTO UserDTO) {


        User User = convertToEntity(UserDTO);
        return convertToDTO(userRepo.save(User));
    }
    
    

    private UserDTO convertToDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getUuid(),
                user.getFirstName(),
                user.getLastName(),
                user.getFavCoffees().stream()
                        .map(c -> new CoffeeDTOPlain(
                                c.getUuid(),
                                c.getName(),
                                c.getHasMilk()
                        ))
                        .collect(Collectors.toSet())
        );
    }

    private User convertToEntity(UserDTO dto) {
        if (dto == null) throw new NullPointerException();
        //Check if we should insert or update user
        Optional<User> userFromDB = Optional.empty();
        if (dto.uuid() != null) {
            userFromDB = userRepo.findDistinctByUuid(dto.uuid());
        }
        User u;
        if (userFromDB.isEmpty()){ //Insert sequence
            u = new User();
            u.setUuid(UUID.randomUUID()); //Generate Random UUID
            u.setFirstName(dto.firstName());
            u.setLastName(dto.lastName());
            u.addFavCoffeeAll(dto.favCoffees().stream()
                    .map(c -> coffeeRepo.findDistinctByUuid(c.uuid()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
            return u;
        }  else { //Update Sequence
            u = userFromDB.get();
            u.setFirstName(dto.firstName());
            u.setLastName(dto.lastName());
            u.addFavCoffeeAll(dto.favCoffees().stream()
                    .map(c -> coffeeRepo.findDistinctByUuid(c.uuid()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
        }
            return userFromDB.get();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    @Override
    public void validate(@NonNull  Object target, @NonNull   Errors errors) {
    }
}
