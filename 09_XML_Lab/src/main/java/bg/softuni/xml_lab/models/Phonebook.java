package bg.softuni.xml_lab.models;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Phonebook {
    private String owner;

    @XmlElementWrapper(name="numbers-wrapper")
    @XmlElement(name = "numbers-item")
    private List<String> numbers;

    @XmlElementWrapper
    @XmlElement(name = "phoneNumber")
    private List<PhoneNumber> phoneNumbers;

    public Phonebook () {
    }

    public Phonebook(String owner, List<String> numbers, List<PhoneNumber> phoneNumbers) {
        this.owner = owner;
        this.numbers = numbers;
        this.phoneNumbers = phoneNumbers;
    }
}
