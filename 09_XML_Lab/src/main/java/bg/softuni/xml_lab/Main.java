package bg.softuni.xml_lab;

import bg.softuni.xml_lab.models.AddressDTO;
import bg.softuni.xml_lab.models.PersonDTO;
import bg.softuni.xml_lab.models.PhoneNumber;
import bg.softuni.xml_lab.models.Phonebook;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        JAXBContext personContext = JAXBContext.newInstance(PersonDTO.class);
        Marshaller marshaller = personContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        AddressDTO addressDTO = new AddressDTO("Bulgaria", "Sofia");
        PersonDTO person = new PersonDTO("Ivan", "Ivanov", 29, addressDTO);

        marshaller.marshal(person, System.out);

//        Unmarshaller personUnmarshaller = personContext.createUnmarshaller();
//        PersonDTO parsed = (PersonDTO) personUnmarshaller.unmarshal(System.in);
//        marshaller.marshal(addressDTO, System.out);

        JAXBContext bookContext = JAXBContext.newInstance(Phonebook.class);
        Marshaller bookMarshaller = bookContext.createMarshaller();
        bookMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        PhoneNumber number1= new PhoneNumber("Ema","515422402");
        PhoneNumber number2= new PhoneNumber("Gosho","515842402");
        PhoneNumber number3= new PhoneNumber("Pesho","515888402");

        Phonebook book = new Phonebook(
                "Gery",
                List.of("Pesho","Ani","Dani"),
                List.of(number1,number2,number3)
        );

        bookMarshaller.marshal(book, System.out);

        Unmarshaller bookUnmarshaller = bookContext.createUnmarshaller();
        Phonebook parsedBook= (Phonebook) bookUnmarshaller.unmarshal(System.in);
        System.out.println();
    }
}
