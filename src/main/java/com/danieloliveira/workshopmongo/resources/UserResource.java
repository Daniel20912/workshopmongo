package com.danieloliveira.workshopmongo.resources;

import com.danieloliveira.workshopmongo.domain.User;
import com.danieloliveira.workshopmongo.dto.UserDTO;
import com.danieloliveira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users") // especifica o caminho do endpoint
// para fazer as operações desse controller precisa colocar o /users na Url http
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    // ResponseEntity encapsula uma estrutura necessária para retornar respostas http com cabeçalhos e códigos de erro
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = new ArrayList<>(userService.findAll());
        List<UserDTO> listDto = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto); // o ok significa que a resposta foi um sucesso
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) { // @PathVariable serve para o id ser reconhecido como uma variável da url
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User obj = userService.fromDTO(userDTO);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build(); // created retorna o código de resposta 201
    }
}
