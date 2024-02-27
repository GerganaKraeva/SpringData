import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Main {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        task12(entityManager);

        entityManager.getTransaction().commit();
    }

    private static void task12(EntityManager entityManager) throws IOException {
        List<Town> resultList = entityManager.createQuery("FROM Town WHERE name = :name", Town.class)
                .setParameter("name",READER.readLine())
                .getResultList();
        if(!resultList.isEmpty()){
            Town town=resultList.get(0);
            List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a JOIN a.town t WHERE t.name = :name", Address.class)
                    .setParameter("name", town.getName())
                    .getResultList();
            addresses.forEach(a->{
                a.getEmployees().forEach(e-> {
                    e.setAddress(null);
                    entityManager.persist(e);
                });
                entityManager.remove(a);
            });

            System.out.printf("%d addresses in %s deleted", addresses.size(), town.getName());
            entityManager.remove(town);
        }

    }

    private static void task11(EntityManager entityManager) {
        entityManager.createQuery("SELECT department.name, max(salary)" +
                        " FROM Employee " +
                        " GROUP BY department.name" +
                        " HAVING max(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach(objects -> System.out.println(objects[0] + " " + objects[1]));

    }

    private static void task10(EntityManager entityManager) throws IOException {
        String userInput = READER.readLine();
        List<Employee> resultList = entityManager.createQuery("FROM Employee WHERE firstName LIKE CONCAT(?1, '%')", Employee.class)
                .setParameter(1, userInput)
                .getResultList();
//        List<Employee> resultList = entityManager.createQuery("FROM Employee WHERE firstName LIKE CONCAT(:letters, '%')", Employee.class)
//                .setParameter("letters", userInput)
//                .getResultList();
        if (!resultList.isEmpty()) {
            resultList.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getJobTitle(),
                    e.getSalary()));
        }
    }


    private static void task9(EntityManager entityManager) {
        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee AS e JOIN e.department AS d " +
                "WHERE d.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class).getResultList();

        for (Employee employee : resultList) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
            entityManager.persist(employee);
            System.out.printf("%s %s ($%.2f)%n", employee.getLastName(), employee.getLastName(), employee.getSalary());
        }
    }

    private static void task8(EntityManager entityManager) {
        entityManager.createQuery("FROM Project ORDER BY name",Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(p-> System.out.printf("Project name: %s%n" +
                        "    Project Description: %s%n"+
                        "    Project Start Date:%s%n"+
                        "    Project End Date:%s%n",
                        p.getName(),
                        p.getDescription(),
                        p.getStartDate(),
                        p.getEndDate()));
    }

    private static void task7(EntityManager entityManager) throws IOException {

        int input= Integer.parseInt(READER.readLine());
        Employee employeeId = entityManager.createQuery("FROM Employee WHERE id = ?1", Employee.class)
                .setParameter(1, input)
                .getSingleResult();

        Set<Project> projects = employeeId.getProjects();
        StringBuilder sb=new StringBuilder();
        projects.stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p->sb.append(p.getName()).append(System.lineSeparator()));

        System.out.printf("%s %s - %s%n%s",employeeId.getFirstName(),employeeId.getLastName(), employeeId.getJobTitle(),sb);
    }

    private static void task6(EntityManager entityManager) {
         entityManager.createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                 .setMaxResults(10)
                 .getResultStream()
                 .forEach(a-> System.out.printf("%s, %s - %d employees%n",
                         a.getText(),
                         a.getTown().getName(),
                         a.getEmployees().size()));
    }

    private static void task5(EntityManager entityManager) throws IOException {

        Town town = entityManager.find(Town.class, 32);
        Address address= new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        entityManager.persist(address);

        String lastName = READER.readLine();
        List<Employee> resultList = entityManager.createQuery("FROM Employee WHERE lastName = :lastName", Employee.class)
                .setParameter("lastName" , lastName)
                .getResultList();


        if (!resultList.isEmpty()) {
            Employee employee= resultList.get(0);
            employee.setAddress(address);
            entityManager.persist(employee);
        }
    }

    private static void task4(EntityManager entityManager) {
   entityManager.createQuery("SELECT e FROM Employee AS e JOIN e.department  AS d WHERE d.name='Research and development' ORDER BY e.salary, e.id",Employee.class)
           .getResultStream()
           .forEach(e -> System.out.printf("%s %s from Research and Development - %.2f%n",
                   e.getFirstName(),
                   e.getLastName(),
                   e.getSalary()));
    }

    private static void task3(EntityManager entityManager) {
//        List <Employee>employee =entityManager.createQuery("FROM Employee WHERE salary > 50000",Employee.class)
//                .getResultList();
//        employee.forEach(e-> System.out.println(e.getFirstName()));

                entityManager.createQuery("FROM Employee WHERE salary > 50000",Employee.class)
                .getResultStream()
                        .map(Employee::getFirstName)
                        .forEach(System.out::println);
    }

    
    private static void task2(EntityManager entityManager) throws IOException {
        String[] input = READER.readLine().split("\\s+");

        List resultList = entityManager.createQuery("FROM  Employee WHERE  firstName = :first_name AND lastName = :last_name")
                .setParameter("first_name", input[0])
                .setParameter("last_name", input[1])
                .getResultList();
        System.out.println(resultList.size() > 0 ? "Yes" : "No");
    }

    private static void task1(EntityManager entityManager) {
        List<Town> resultList = entityManager.createQuery("FROM Town WHERE  LENGTH (name) > 5", Town.class).getResultList();
        resultList.forEach(town -> {
            town.setName(town.getName().toUpperCase());
            entityManager.persist(town);
        });
    }
}
