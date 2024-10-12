import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class User {

    private String name;
    private int age;

    public User(String name,int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static List<User> getTopFiveOldest(List<User> users) {
        return users.stream()
                .collect(Collectors.toMap((User::getName), Function.identity(), (u1, u2)->u1.getAge()>= u2.getAge() ? u1:u2))
                .values()
                .stream()
                .sorted(Comparator.comparing(User::getAge).reversed()
                        .thenComparing(User::getName))
                        .limit(5)
                .collect(Collectors.toList());
    }

    public static void main(String [] args)
    {
        List<User>users = List.of(
                new User("Лиза", 32),
                new User("Марк", 25),
                new User("Дарья",35),
                new User("Михаил", 40),
                new User("Максим", 30),
                new User("Лиза", 32),
                new User("Марк", 25)
        );
        List<User> topFive = getTopFiveOldest(users);
        topFive.forEach(System.out::println);
    }
}