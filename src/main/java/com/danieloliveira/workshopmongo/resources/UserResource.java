package com.danieloliveira.workshopmongo.resources;

import com.danieloliveira.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users") // especifica o caminho do endpoint
// para fazer as operações desse controller precisa colocar o /users na Url http
public class UserResource {

    @GetMapping
    // ResponseEntity encapsula uma estrutura necessária para retornar respostas http com cabeçalhos e códigos de erro
    public ResponseEntity<List<User>> findAll() {
        User maria = new User("1", "Maria Brown", "maria@gmail.com");
        User alex = new User("2", "Alex", "alex@gmail.com");
        List<User> list = new ArrayList<>(Arrays.asList(maria, alex));
        return ResponseEntity.ok().body(list); // o ok significa que a resposta foi um sucesso
    }
}
