package com.nixiedroid.rest.services;

import com.nixiedroid.rest.dto.UserDTO;
import com.nixiedroid.rest.interfaces.UserRepository;
import com.nixiedroid.rest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
public class UserSvc  implements Validator {

    private final UserRepository repo;

    @Autowired
    public UserSvc(UserRepository repo) {
        this.repo = repo;
    }


    public UserDTO getUserById(Long UserId) {
        User User = repo.findById(UserId).orElse(null);
        return convertToDTO(User);
    }

    public List<UserDTO> findAll() {
        //return repo.findAll().stream().map(c -> convertToDTO(c)).toList();
        return repo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<UserDTO> findById(long id){
        return repo.findById(id).map(this::convertToDTO);
    }

    public boolean existsById(long id){
        return repo.existsById(id);
    }
    public void deleteById(long id){
        repo.deleteById(id);
    }

    public UserDTO save(UserDTO UserDTO) {
        User User = convertToEntity(UserDTO);
        return convertToDTO(repo.save(User));
    }
    
    

    private UserDTO convertToDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    private User convertToEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        User u = repo.findDistinctByFirstNameAndLastName(userDTO.getFirstName(),userDTO.getLastName());
        if (u == null){
            u = new User();
            u.setFirstName(userDTO.getFirstName());
            u.setLastName(userDTO.getLastName());
            return u;
        }
            return u;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
            return;
    }
}
