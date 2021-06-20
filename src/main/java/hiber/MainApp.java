package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car car = new Car("YAZ", 123);

        userService.add(new User("Ivan", "Ivanov", "ivanov@mail.ru", car));
        userService.add(new User("Petr", "Petrov", "petrov@mail.ru", new Car("BMW", 111)));

        List<Car> cars = userService.listCars();
        for (Car car1 : cars) {
           System.out.println("Id = " + car1.getId());
           System.out.println("Model = " + car1.getModel());
           System.out.println("Series = " + car1.getSeries());
           System.out.println();
        }

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<User> users1 = userService.getUserByCar("BMW", 111);
        for (User user1: users1) {
            System.out.println(user1);
        }

        context.close();
    }
}
