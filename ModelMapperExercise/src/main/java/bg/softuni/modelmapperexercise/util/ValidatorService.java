package bg.softuni.modelmapperexercise.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface ValidatorService {
   <E> boolean isValid (E entity);
   <E> Set <ConstraintViolation <E>> validate (E entity);
}
