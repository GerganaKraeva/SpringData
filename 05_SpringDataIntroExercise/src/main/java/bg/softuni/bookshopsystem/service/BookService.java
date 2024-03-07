package bg.softuni.bookshopsystem.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException ;

    List<String> findAllBooksAfterYear2000();

    List <String> findAllBookByGeorgePowellOrdered();


    void createBook();
}
