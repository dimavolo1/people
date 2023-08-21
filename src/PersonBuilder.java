import java.util.OptionalInt;

public class PersonBuilder {
    private String name;
    private String surname;
    private String address;
    private OptionalInt age;

    public PersonBuilder() {
        age = OptionalInt.empty();
    }

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0 || age > 200) throw new IllegalArgumentException("Передан некорректный возраст");
        this.age = OptionalInt.of(age);
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        if (stringNullOrBlank(name) || stringNullOrBlank(surname))
            throw new IllegalStateException("Невозможно создать объект без имени и фамилии");

        Person person = new Person(name, surname);
        if (age.isPresent()) {
            person.setAge(age.getAsInt());
        }
        person.setAddress(address);
        return person;
    }

    private boolean stringNullOrBlank(String val) {
        return val == null || val.isEmpty() || val.isBlank();
    }
}