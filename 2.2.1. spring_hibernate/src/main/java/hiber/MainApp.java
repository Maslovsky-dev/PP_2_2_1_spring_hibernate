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
      User user1 = new User("Carl", "Rick", "test@mail.ru");
      Car car1 = new Car("Lada");
      Car car2 = new Car("Mercedes");
      Car car3 = new Car("BMW");
      Car car4 = new Car("Tesla");
      userService.add(new User("Carl", "Rick", "test@mail.ru",car1));
      userService.add(new User("Jon", "Siena", "Jon@Siena.ru",car2));
      userService.add(new User("Ed", "Snowden", "Snow@den.ru",car3));
      userService.add(new User("Jeff", "Bezos", "J@Bz.ru",car4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().toString());
         System.out.println();
      }

      context.close();
   }
}
