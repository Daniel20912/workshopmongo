package com.danieloliveira.workshopmongo.repositories;

import com.danieloliveira.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    // query method que busca o post com o título passado
    List<Post> findByTitleContainingIgnoreCase(String text);
}
