package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
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
          System.out.println(user.toString());
      }

      try {
         User ladaUser = userService.getUserByCar(3, "BMW");
         System.out.println("\n Пользователь с машиной найден:\n" + ladaUser.toString());
      } catch (NoResultException e) {
         System.out.println("\nНет пользователя с такой машиной\n");
      }
      context.close();
   }
}
