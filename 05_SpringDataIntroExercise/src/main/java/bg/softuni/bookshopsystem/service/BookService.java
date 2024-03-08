package bg.softuni.bookshopsystem.service;

import bg.softuni.bookshopsystem.data.entities.Book;
import bg.softuni.bookshopsystem.data.entities.enums.AgeRestriction;
import bg.softuni.bookshopsystem.data.entities.enums.EditionType;
import bg.softuni.bookshopsystem.data.repositories.BookInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException ;

    List<String> findAllBooksAfterYear2000();

    List <String> findAllBookByGeorgePowellOrdered();


    void createBook();

    List <String> findAllByAgeRestriction(AgeRestriction ageRestriction);

   List <String> findAllByEditionAndCopies(EditionType type, int copies);


   List<Book> findAllBooksWithPriceOutsideOf(int lower, int higher);

   List<String> findAllTitlesWithReleaseDateNotBetween(int year);


    List<Book> findAllReleasedBefore(LocalDate date);

    List <String> getTitlesOfBooksContaining(String string);

    List <String> getTitlesOfBooksWithAuthorLastNameStarting(String string);

    int getTitleCountLongerThan(int minLength);

    BookInfo getInfoByTitle(String title);

    void sellCopies(int bookId, int copiesSold);
}
