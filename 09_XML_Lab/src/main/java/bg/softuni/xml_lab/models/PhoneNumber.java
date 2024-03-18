package bg.softuni.xml_lab.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumber {
    private String name;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
