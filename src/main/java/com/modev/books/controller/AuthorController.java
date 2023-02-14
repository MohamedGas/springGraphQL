package com.modev.books.controller;

import com.modev.books.Repository.AuthorRepository;
import com.modev.books.model.Author;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Author> allAuthors(){
        return authorRepository.findAll();
    }
    @QueryMapping
    public Optional<Author> findOneAuthor(@Argument Integer id){
       return  authorRepository.findById(id);
    }

    @MutationMapping
    public Author saveAuthor(@Argument Author author){
        Author newAuthor = authorRepository.saveAuthor(author);
        return newAuthor;
    }
    @MutationMapping
    public Optional<Author> updateAuthor(@Argument Integer id, @Argument Author author){
        Optional<Author> author1 = authorRepository.updateAuthor(id, author);
        return author1;

    }

}
