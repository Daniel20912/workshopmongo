package com.danieloliveira.workshopmongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

// indica que a classe corresponde a uma coleção do mongoDb
@Document(collection = "user") // assim se dá um nome específico para a collection, mas por padrão o nome será o nome da classe tudo em minúsculo
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // serve para que os objetos sejam transformados em bytes para poderem trafegar em rede e gravados em arquivos

    @Id // Em MongoDB, não precisa usar a anotação @GeneratedValue (como em bancos de dados relacionais com JPA) para gerar automaticamente os IDs
    private String id;
    private String name;
    private String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
