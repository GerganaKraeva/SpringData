package bg.softuni.bookshopsystem.data.repositories;

import bg.softuni.bookshopsystem.data.entities.enums.AgeRestriction;
import bg.softuni.bookshopsystem.data.entities.enums.EditionType;

import java.math.BigDecimal;

public interface BookInfo {
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();
}
