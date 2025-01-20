package com.danieloliveira.workshopmongo.services;

import com.danieloliveira.workshopmongo.domain.Post;
import com.danieloliveira.workshopmongo.repositories.PostRepository;
import com.danieloliveira.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
