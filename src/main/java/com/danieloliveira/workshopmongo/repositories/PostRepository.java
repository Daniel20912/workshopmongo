package com.danieloliveira.workshopmongo.repositories;

import com.danieloliveira.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    /*
       Consulta personalizada usando a anotação @Query
       'title': Campo no documento MongoDB que será pesquisado
       $regex: ?0: Usa uma expressão regular para buscar correspondências no campo title. O marcador "?0" refere-se ao primeiro parâmetro passado para o metodo searchTitle. No caso, será a variável text.
       $options: 'i': Indica que a busca deve ser case-insensitive (ignora diferenças entre maiúsculas e minúsculas).
     */
    @Query("{'title' :  {$regex: ?0, $options: 'i'}}")
    List<Post> searchTitle(String text);

    // query method que busca o post com o título passado
    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
