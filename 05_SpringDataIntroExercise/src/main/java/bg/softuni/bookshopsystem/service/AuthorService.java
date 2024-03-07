package bg.softuni.bookshopsystem.service;

import bg.softuni.bookshopsystem.data.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();


    List<String> getAllAuthorsFirstAndLastNameForBooksBeforeYear1990();

    List<String> getAllAuthorsDescBooks();
}
