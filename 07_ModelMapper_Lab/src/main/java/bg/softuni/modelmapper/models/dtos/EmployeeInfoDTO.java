package bg.softuni.modelmapper.models.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeInfoDTO {
      private  String firstName;
      private  String lastName;

      private BigDecimal salary;

      private LocalDate birthday;

      public EmployeeInfoDTO () {}

    public EmployeeInfoDTO(String firstName, String lastName, BigDecimal salary, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfoDTO that = (EmployeeInfoDTO) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(salary, that.salary) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary, birthday);
    }
}
