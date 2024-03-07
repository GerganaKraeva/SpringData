package bg.softuni.bookshopsystem;

import bg.softuni.bookshopsystem.data.entities.Category;
import bg.softuni.bookshopsystem.service.AuthorService;
import bg.softuni.bookshopsystem.service.BookService;
import bg.softuni.bookshopsystem.service.CategoryService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final AuthorService authorService;

    private final CategoryService categoryService;

    private final BookService bookService;

    public CommandLineRunnerImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
//        getAllBooksAfter2000Year();
//        getAuthorsFirstAndLastNameBeforeBooks1990();
//        getAllAuthorsByBooksDesc();
//        printBooksByGeorgePower();
        this.bookService.createBook();
    }

    private void getAuthorsFirstAndLastNameBeforeBooks1990() {
        this.authorService.getAllAuthorsFirstAndLastNameForBooksBeforeYear1990()
                .forEach(System.out::println);
    }

    private void printBooksByGeorgePower() {
        this.bookService.findAllBookByGeorgePowellOrdered()
                .forEach(System.out::println);
    }

    private void getAllAuthorsByBooksDesc() {

        this.authorService.getAllAuthorsDescBooks()
                .forEach(System.out::println);
    }



    private void getAllBooksAfter2000Year() {
        this.bookService.findAllBooksAfterYear2000()
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
