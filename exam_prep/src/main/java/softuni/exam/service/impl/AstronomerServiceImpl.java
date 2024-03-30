package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.service.AstronomerService;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AstronomerServiceImpl implements AstronomerService {
    private final String PATH_ASTRONOMERS_XML="src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository) {
        this.astronomerRepository = astronomerRepository;
    }


    @Override
    public boolean areImported() {
        return this.astronomerRepository.count()>0;
    }


    @Override
    public String readAstronomersFromFile() throws IOException {
        return new String(Files.readString(Path.of(PATH_ASTRONOMERS_XML)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        return null;
    }
}
