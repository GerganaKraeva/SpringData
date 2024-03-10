package bg.softuni.modelmapper.services;

import bg.softuni.modelmapper.models.dtos.EmployeeInfoDTO;
import bg.softuni.modelmapper.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeInfoDTO> findInfoForBornBefore(LocalDate before) {
        return   employeeRepository.findAllByBirthdayBefore(before);
    }
}
