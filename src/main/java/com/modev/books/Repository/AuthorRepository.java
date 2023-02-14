package com.modev.books.Repository;

import com.modev.books.model.Author;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    public List<Author> findAll() {
        return authors;
    }

    public Optional<Author> findById(int id) {
        Optional<Author> author = Optional.ofNullable(authors.stream()
                .filter(a -> a.id() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found")));
        return author;
    }

    public Author findByName(String name) {
        return authors.stream()
                .filter(author -> author.fullName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }
    public Author saveAuthor(Author author){
        authors.add(author);
        return author;
    }
    public Optional<Author> updateAuthor(Integer id, Author author){
        Author newAuthor = new Author(author.id(),author.firstName(),author.lastName());
        Optional<Author> existAuthor = authors
                                    .stream()
                                    .filter(a-> a.id()==id)
                                    .findFirst();
        if(existAuthor.isPresent()){
            Author author1 = existAuthor.get();
            int index = authors.indexOf(author1);
            authors.set(index,newAuthor);
        }
        else {
            throw new RuntimeException("Not Found ");
        }

        return Optional.ofNullable(author);
    }

    @PostConstruct
    private void init() {
        authors.add(new Author(1,"Josh","Long"));
        authors.add(new Author(2,"Mark","Heckler"));
        authors.add(new Author(3,"Greg","Turnquist"));
    }
}