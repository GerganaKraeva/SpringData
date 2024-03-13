package bg.softuni.json_lab;

import bg.softuni.json_lab.dtos.AddressDTO;
import bg.softuni.json_lab.dtos.PersonDTO;
import bg.softuni.json_lab.entities.Person;
import bg.softuni.json_lab.services.PersonService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Main implements CommandLineRunner {
//   @Autowired
//   @Qualifier("withoutNulls")
    private Gson gson;
    private PersonService personService;
    private int connectionCount;


    private ModelMapper modelMapper;
    @Autowired
    public Main(
            @Qualifier("withoutNulls") Gson gson,
            @Value("${yourproject.yourkey.connectionCount}") int connectionCount,
            PersonService personService,
            ModelMapper modelMapper
    ) {
        this.gson=gson;
        this.personService = personService;
        this.connectionCount = connectionCount;
        this.modelMapper = modelMapper;
    }



    @Override
    public void run(String... args) throws Exception {
//        Gson gson = new GsonBuilder()
////                .excludeFieldsWithoutExposeAnnotation()
//                .setPrettyPrinting()
//                .serializeNulls()
//                .create();

        printJson(gson);

//        readJson(gson);


    }

    private void readJson(Gson gson) {
        String json = """
                [
                {
                  "firstName": "Aleksandra",
                  "lastName": null,
                  "age": 25,
                  "isMarried": true,
                  "lotteryNumbers": [
                    1,
                    2,
                    5,
                    22,
                    45
                  ],
                  "address": {
                    "country": "Bg",
                    "city": "Sofia"
                  }
                },
                  {
                  "firstName": "Ivan",
                  "lastName": null,
                  "age": 45,
                  "isMarried": true,
                  "lotteryNumbers": [
                    8,
                    2,
                    7,
                    25,
                    5
                  ],
                  "address": {
                    "country": "Bg",
                    "city": "Plovdiv"
                  }
                }
                ]
                """;
        PersonDTO [] personDTO = gson.fromJson(json, PersonDTO[].class);
        List<Person> realPeople =new ArrayList<>();

        for (PersonDTO dto : personDTO) {
            Person person = modelMapper.map(dto, Person.class);
            realPeople.add(person);
        }
        for (Person realPerson : realPeople) {
            System.out.println(realPerson);
        }

    }

    private static void printJson(Gson gson) {
        PersonDTO personDTO = new PersonDTO(
                "Aleksandra",
                null,
                25,
                true,
                List.of(1, 2, 5, 22, 45),
                new AddressDTO("Bg", "Sofia")
        );

        String json = gson.toJson(personDTO);
//        String json = gson.toJson(List.of(personDTO, personDTO));


        System.out.println(json);
    }
}

