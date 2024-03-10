package bg.softuni.modelmapper.repositories;

import bg.softuni.modelmapper.models.dtos.EmployeeInfoDTO;
import bg.softuni.modelmapper.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer > {

   @Query("SELECT new bg.softuni.modelmapper.models.dtos.EmployeeInfoDTO(e.firstName, e.lastName, e.salary, e.birthday) " +
           "FROM  Employee e WHERE e.birthday < :before")
    List<EmployeeInfoDTO> findAllByBirthdayBefore(LocalDate before);


}
