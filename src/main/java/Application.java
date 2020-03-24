import movie.theater.domain.User;
import movie.theater.service.UserService;
import movie.theater.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Scanner;

public class Application {

    private static void showMenu() {
        System.out.println("1 - USER --> GET USER BY EMAIL");
        System.out.println("2 - USER --> REMOVE USER");
        System.out.println("3 - USER --> GET USER BY ID");
        System.out.println("4 - USER --> ADD/SAVE USER");
        System.out.println("5 - USER --> GET ALL USERS");
        System.out.println("6 - EVENT --> GET NEXT EVENTS");
        System.out.println("7 - EVENT --> GET FOR DATE RANGE");
        System.out.println("8 - DISCOUNT --> GET DISCOUNT");
        System.out.println("9 - BOOKING --> GET TICKETS PRICE");
        System.out.println("10- BOOKING --> BOOK TICKETS");
        System.out.println("11- BOOKING --> GET PURCHASED TICKETS FOR EVENT");
        System.out.println("12- AUDITORIUM --> GET ALL");
        System.out.println("13- AUDITORIUM --> GET BY NAME");
        System.out.println("14- QUIT");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        UserService userService = applicationContext.getBean(UserServiceImpl.class);

        Scanner input = new Scanner(System.in);
        User user;

        System.out.println("--------------------- Movie Theater program ---------------------");

        boolean quitFlag = false;

        while (!quitFlag) {
            showMenu();
            System.out.print("Enter choice [1-14]: ");
            int choiceMainMenu = input.nextInt();
            switch (choiceMainMenu) {
                case 1:
                    user = getUserByEmail(userService, input);
                    if (user != null)
                        System.out.println(user);
                    else
                        System.out.println("User not found");
                    break;
                case 2:
                    user = getUserById(userService, input);
                    userService.remove(user);
                    System.out.println("User removed");
                    break;
                case 3:
                    user = getUserById(userService, input);
                    System.out.println(user);
                    break;
                case 4:
                    addUser(userService, input);
                    break;
                case 5:
                    printUsers(userService.getAll());
                case 14:
                    System.out.println("Thank you for using Movie Theater program");
                    quitFlag = true;
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private static void printUsers(Collection<User> users) {
        for (User user : users)
            System.out.println(user);
    }

    private static User getUserById(UserService userService, Scanner input) {
        try {
            System.out.println("Enter user id: ");
            Long id = input.nextLong();
            return userService.getById(id);
        } catch (RuntimeException r) {
            return null;
        }

    }

    private static User getUserByEmail(UserService userService, Scanner input) {
        System.out.println("Enter user email: ");
        String email = input.next();
        return userService.getUserByEmail(email);
    }

    private static void addUser(UserService u, Scanner input) {
        System.out.println("Enter first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter last name: ");
        String lastName = input.nextLine();
        System.out.println("Enter email: ");
        String email = input.nextLine();
        u.save(new User(firstName, lastName, email, null));
    }

}
