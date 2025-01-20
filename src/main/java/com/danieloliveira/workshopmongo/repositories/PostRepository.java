package com.danieloliveira.workshopmongo.repositories;

import com.danieloliveira.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
