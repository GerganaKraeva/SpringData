package bg.softuni.modelmapper.lab;

import bg.softuni.modelmapper.lab.models.Employee;
import bg.softuni.modelmapper.lab.models.EmployeeDTO;
import bg.softuni.modelmapper.lab.models.ManagerDTO;
import bg.softuni.modelmapper.lab.models.WorkStatus;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LabMain {
    public static void main(String[] args) {
        ModelMapper mapper=new ModelMapper();
        Employee manager = new Employee("Manager", "LManager", BigDecimal.TEN, LocalDate.now(),"Plovdiv", WorkStatus.PAID_TIME_OFF, null, List.of());

        Employee employee =
                new Employee("First", "Last", BigDecimal.ONE, LocalDate.now(), "Sofia",
                        WorkStatus.PRESENT,manager, List.of());

        manager.setEmployees(List.of(employee));

        EmployeeDTO result = mapper.map(employee, EmployeeDTO.class);
        ManagerDTO managerDTO=mapper.map(manager, ManagerDTO.class);
        System.out.println(result);
        System.out.println(managerDTO);

    }
}
