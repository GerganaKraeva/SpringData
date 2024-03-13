package bg.softuni.json_lab.entities;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private boolean isMarries;

    public Person() {
    }

    public Person(String firstName, String lastName, int age, boolean isMarries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age=age;
        this.isMarries = isMarries;
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

    public void setLastName(String lasttName) {
        this.lastName = lasttName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarries() {
        return isMarries;
    }

    public void setMarries(boolean marries) {
        isMarries = marries;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isMarries=" + isMarries +
                '}';
    }
}
