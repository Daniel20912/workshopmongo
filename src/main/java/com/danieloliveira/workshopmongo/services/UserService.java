package com.danieloliveira.workshopmongo.services;

import com.danieloliveira.workshopmongo.domain.User;
import com.danieloliveira.workshopmongo.dto.UserDTO;
import com.danieloliveira.workshopmongo.repositories.UserRepository;
import com.danieloliveira.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id); // se ele não achar o objeto pelo id, a excessão já será lançada no metodo findById
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        User user = userRepository.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        updateData(user, obj);
        return userRepository.save(user);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    // cria um user a partir de um DTO
    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
