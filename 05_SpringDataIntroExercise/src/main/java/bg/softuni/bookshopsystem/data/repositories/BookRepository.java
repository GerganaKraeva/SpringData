package bg.softuni.bookshopsystem.data.repositories;

import bg.softuni.bookshopsystem.data.entities.Book;
import bg.softuni.bookshopsystem.data.entities.enums.AgeRestriction;
import bg.softuni.bookshopsystem.data.entities.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

    //FROM Book b WHERE b.releaseDate > ?1
    Set<Book> findAllByReleaseDateAfter(LocalDate date);

    Set<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List <Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal higher);

    List <Book> findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate startDate, LocalDate endDate);

   List <Book> findAllByReleaseDateBefore(LocalDate date);

   List <Book> findAllByTitleContaining(String string);

   List <Book> findAllByAuthorLastNameStartingWith(String string);

   @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH (b.title) > :min")
   int countByTitleContains(int min);

   BookInfo findByTitle(String title);


   @Query("UPDATE Book b " +
           "SET b.copies = b.copies + :additionalCopies " +
           "WHERE b.id= :id")
   @Modifying
   @Transactional
   int updateBookCopiesById(int id, int additionalCopies);


   void deleteByCopiesLessThan(int copies);


//   @Query(nativeQuery = true, "CALL ...")
    void callProcedure();


}

