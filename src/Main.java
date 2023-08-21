public class Main {
    public static void main(String[] args) {
        Person mom = new PersonBuilder().setName("Анна").setSurname("Вольф").setAge(31).setAddress("Сидней").build();
        Person son = mom.newChildBuilder().setName("Антошка").build();
        System.out.println("У " + mom + " есть сын, " + son);

        if (mom.hasAge()) {
            mom.happyBirthday();
            System.out.println("Прошло день рождение мымы, ей теперь " + mom.getAge().getAsInt());
        }
        try {
            // Не хватает обяхательных полей
            new PersonBuilder().setName("Анна").build();
            new PersonBuilder().setSurname("Иванова").build();
            new PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            // Возраст недопустимый
            new PersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
