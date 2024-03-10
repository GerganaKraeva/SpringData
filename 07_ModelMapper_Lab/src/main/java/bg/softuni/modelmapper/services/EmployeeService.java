package bg.softuni.modelmapper.services;

import bg.softuni.modelmapper.models.dtos.EmployeeInfoDTO;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    List<EmployeeInfoDTO> findInfoForBornBefore(LocalDate before);

}
