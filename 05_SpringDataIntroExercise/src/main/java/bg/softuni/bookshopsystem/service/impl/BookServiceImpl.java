package bg.softuni.bookshopsystem.service.impl;

import bg.softuni.bookshopsystem.data.entities.Author;
import bg.softuni.bookshopsystem.data.entities.Book;
import bg.softuni.bookshopsystem.data.entities.Category;
import bg.softuni.bookshopsystem.data.entities.enums.AgeRestriction;
import bg.softuni.bookshopsystem.data.entities.enums.EditionType;
import bg.softuni.bookshopsystem.data.repositories.BookInfo;
import bg.softuni.bookshopsystem.data.repositories.BookRepository;
import bg.softuni.bookshopsystem.service.AuthorService;
import bg.softuni.bookshopsystem.service.BookService;
import bg.softuni.bookshopsystem.service.CategoryService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private static final String FILE_PATH = "src/main/resources/files/books.txt";

    private final CategoryService categoryService;

    private final AuthorService authorService;
    private final BookRepository bookRepository;

    private Validator validator;

    public BookServiceImpl(CategoryService categoryService, AuthorService authorService, BookRepository bookRepository) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.validator= Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();
    }

    public void seedBooks() throws IOException {
        if (this.bookRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] data = row.split("\\s+");


                        Author author = this.authorService.getRandomAuthor();

                        EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

                        LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        int copies = Integer.parseInt(data[2]);
                        BigDecimal price = new BigDecimal(data[3]);
                        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                        String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));
                        Set<Category> categories = this.categoryService.getRandomCategories();
                        Book book = new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories);
                        this.bookRepository.saveAndFlush(book);
                    });

        }
    }

    @Override
    public List<String> findAllBooksAfterYear2000() {

        return this.bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000, 12, 31))
                .stream()
                .map(book -> String.format("%s %s", book.getTitle(), book.getReleaseDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookByGeorgePowellOrdered() {
        return this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell")
                .stream()
                .map(b->String.format("%s %s %d",b.getTitle(), b.getReleaseDate(), b.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public void createBook() {

        Book notValidBook = new Book(null, EditionType.NORMAL, BigDecimal.ONE, 5, LocalDate.now(), AgeRestriction.ADULT, this.authorService.getRandomAuthor(),
                this.categoryService.getRandomCategories());
        Book validBook = new Book("Love", EditionType.NORMAL, BigDecimal.ONE, 5, LocalDate.now(), AgeRestriction.ADULT, this.authorService.getRandomAuthor(),
                this.categoryService.getRandomCategories());

        Set<ConstraintViolation<Book>> validateNotValid = this.validator.validate(notValidBook);
        Set<ConstraintViolation<Book>> validValidation = this.validator.validate(validBook);
        if(validValidation.isEmpty()){
            this.bookRepository.saveAndFlush(validBook);
        }

        System.out.println();

    }

    @Override
    public List<String> findAllByAgeRestriction(AgeRestriction restriction) {
        return bookRepository.findAllByAgeRestriction(restriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByEditionAndCopies(EditionType editionType, int copies) {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType,copies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllBooksWithPriceOutsideOf(int lower, int higher) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40));
    }

    @Override
    public List<String> findAllTitlesWithReleaseDateNotBetween(int year) {
        return bookRepository.findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate.of(year,01,01),
                LocalDate.of(year,12,31))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllReleasedBefore(LocalDate date) {
         return this.bookRepository.findAllByReleaseDateBefore(date);
    }

    @Override
    public List<String> getTitlesOfBooksContaining(String string) {
        return this.bookRepository.findAllByTitleContaining(string)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTitlesOfBooksWithAuthorLastNameStarting(String string) {
        List<Book> allByAuthorLastNameStartingWith = bookRepository.findAllByAuthorLastNameStartingWith(string);
        List <String> formattedList=new ArrayList<>();
        for (Book book : allByAuthorLastNameStartingWith) {
            String formattedTitle = String.format("%s (%s %s)", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName());
            formattedList.add(formattedTitle);

        }

            return formattedList;
    }

    @Override
    public int getTitleCountLongerThan(int minLength) {
        return  this.bookRepository.countByTitleContains(minLength);
    }

    @Override
    public BookInfo getInfoByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    @Override
    public void sellCopies(int bookId, int copiesSold) {
        this.bookRepository.updateBookCopiesById(bookId,copiesSold);
    }


}

