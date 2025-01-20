package com.danieloliveira.workshopmongo.config;

import com.danieloliveira.workshopmongo.domain.Post;
import com.danieloliveira.workshopmongo.domain.User;
import com.danieloliveira.workshopmongo.dto.AuthorDTO;
import com.danieloliveira.workshopmongo.dto.CommentDTO;
import com.danieloliveira.workshopmongo.repositories.PostRepository;
import com.danieloliveira.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Configuration // essa classe é executada toda vez que o projeto rodar
public class Instantiation implements CommandLineRunner {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) {

        // deleta e instancia as collections sempre que o programa for executado novamente

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        // é preciso adicionar o user ao banco de dados antes de criar um AuthorDTO dele, pois ele só vai receber um Id após ser adicionado ao banco de dados
        Post post1 = new Post(null, LocalDate.parse("21/03/2018", dtf), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, LocalDate.parse("23/03/2018", dtf), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano", LocalDate.parse("21/03/2018", dtf), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", LocalDate.parse("22/03/2018", dtf), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", LocalDate.parse("23/03/2018", dtf), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);

    }
}
