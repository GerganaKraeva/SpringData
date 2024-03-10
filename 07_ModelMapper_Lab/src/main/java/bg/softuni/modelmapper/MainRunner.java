package bg.softuni.modelmapper;

import bg.softuni.modelmapper.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MainRunner implements CommandLineRunner {

   private EmployeeService employeeService;

    public MainRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//        Address address =new Address("Bulgaria", "Plovdiv","Marica");
//        Employee employee=new Employee("First","Last", BigDecimal.TEN,address, LocalDate.now());

        ModelMapper modelMapper = new ModelMapper();

//        BasicEmployeeDTO employeeDto = modelMapper.map(employee, BasicEmployeeDTO.class);


        employeeService.findInfoForBornBefore(LocalDate.of(1990, 01, 01));
        System.out.println("Hello World!");
    }
}
