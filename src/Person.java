import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    private final String name;
    private final String surname;
    private String address;
    private OptionalInt age;

    public boolean hasAge() {
        return age.isPresent();
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        age = OptionalInt.empty();
    }

    public Person(String name, String surname, String address, int age) {
        this(name, surname);
        this.address = address;
        this.age = OptionalInt.of(age);
    }

    public boolean hasAddress() {
        return address != null && !address.isEmpty() && !address.isBlank();
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public OptionalInt getAge() {
        if (age.isEmpty() || age == null) {
            throw new IllegalArgumentException("Некорректный возраст");
        }
        return age;
    }

    public void setAge(int age) {
        if (hasAge()) throw new IllegalStateException("Невозможно присвоить новый возраст");
        this.age = OptionalInt.of(age);

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String city) {
        this.address = city;
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder().setSurname(surname).setAddress(address).setAge(0);
    }

    public void happyBirthday() throws IllegalArgumentException {
        if (age.isPresent()) {
            age = OptionalInt.of(age.getAsInt() + 1);
        } else {
            throw new IllegalArgumentException("Невозможно отпразновать ДР, так как мы не знаем возраст");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && surname.equals(person.surname) && Objects.equals(address, person.address) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, address, age);
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + ", address='" + (addressIsNotEmpty() ? address : "нету") + '\'' + ", age=" + (age.isPresent() ? age.getAsInt() : "нету") + '}';
    }

    private boolean addressIsNotEmpty() {
        return address != null && !address.isEmpty() && address.isBlank();
    }
}
