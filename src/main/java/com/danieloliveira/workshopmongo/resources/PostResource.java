package com.danieloliveira.workshopmongo.resources;

import com.danieloliveira.workshopmongo.domain.Post;
import com.danieloliveira.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) { // @PathVariable serve para o id ser reconhecido como uma variável da url
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
