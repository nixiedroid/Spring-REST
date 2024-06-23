package com.nixiedroid.rest.services;

import com.nixiedroid.rest.dto.CoffeeDTO;
import com.nixiedroid.rest.interfaces.CoffeeRepository;
import com.nixiedroid.rest.models.Coffee;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeSvc implements Validator {
    /**
     * Coffee repository accessor <br>
     * {@link org.springframework.beans.factory.annotation.Autowired}
     */
    private final CoffeeRepository repo;


    public CoffeeSvc(CoffeeRepository repo) {
        this.repo = repo;
    }

    public CoffeeDTO getcoffeeById(Long coffeeId) {
        Coffee coffee = repo.findById(coffeeId).orElse(null);
        return convertToDTO(coffee);
    }

    public List<CoffeeDTO> findAll() {
        //return repo.findAll().stream().map(c -> convertToDTO(c)).toList();
        return repo.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<CoffeeDTO> findById(long id){
        return repo.findById(id).map(this::convertToDTO);
    }
    
    public boolean existsById(long id){
        return repo.existsById(id);
    }
    public void deleteById(long id){
        repo.deleteById(id);
    }

    public CoffeeDTO save(CoffeeDTO coffeeDTO) {
        Coffee coffee = convertToEntity(coffeeDTO);
        return convertToDTO(repo.save(coffee));
    }

    private CoffeeDTO convertToDTO(Coffee coffee) {
        if (coffee == null) return null;
        CoffeeDTO dto = new CoffeeDTO();
        dto.setName(coffee.getName());
        dto.setHasMilk(coffee.getHasMilk());
        return dto;
    }

    private Coffee convertToEntity(CoffeeDTO dto) {
        if (dto == null) return null;
        Coffee c = repo.findDistinctByNameAndHasMilk(dto.getName(), dto.isHasMilk());
        if (c == null) {
            c = new Coffee();
            c.setName(dto.getName());
            c.setHasMilk(dto.isHasMilk());
            return c;
        }
        return c;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CoffeeDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        return;
    }
}
