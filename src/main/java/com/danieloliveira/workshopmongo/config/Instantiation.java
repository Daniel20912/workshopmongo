package com.danieloliveira.workshopmongo.config;

import com.danieloliveira.workshopmongo.domain.Post;
import com.danieloliveira.workshopmongo.domain.User;
import com.danieloliveira.workshopmongo.repositories.PostRepository;
import com.danieloliveira.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration // essa classe é executada toda vez que o projeto rodar
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // deleta e instancia as collections sempre que o programa for executado novamente

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, LocalDate.parse("21/03/2018", dtf), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", maria);
        Post post2 = new Post(null, LocalDate.parse("23/03/2018", dtf), "Bom dia", "Acordei feliz hoje!", maria);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));

    }
}
