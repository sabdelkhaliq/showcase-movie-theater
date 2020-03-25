import movie.theater.domain.Event;
import movie.theater.domain.User;
import movie.theater.operations.EventOperations;
import movie.theater.operations.UserOperations;
import movie.theater.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class Application {

    private static void showMenu() {
        System.out.println("1 - USER --> GET USER BY EMAIL");
        System.out.println("2 - USER --> REMOVE USER");
        System.out.println("3 - USER --> GET USER BY ID");
        System.out.println("4 - USER --> ADD/SAVE USER");
        System.out.println("5 - USER --> GET ALL USERS");

        System.out.println("6 - EVENT --> GET EVENT BY NAME");
        System.out.println("7 - EVENT --> REMOVE EVENT");
        System.out.println("8 - EVENT --> GET EVENT BY ID");
        System.out.println("9 - EVENT --> ADD/SAVE EVENT");
        System.out.println("10 - EVENT --> GET ALL EVENTS");

        System.out.println("11 - EVENT --> GET NEXT EVENTS");
        System.out.println("12 - EVENT --> GET FOR DATE RANGE");

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
        EventService eventService = applicationContext.getBean(EventServiceImpl.class);
        AuditoriumService auditoriumService = applicationContext.getBean(AuditoriumServiceImpl.class);

        EventOperations eventOperations = applicationContext.getBean(EventOperations.class);
        UserOperations userOperations = applicationContext.getBean(UserOperations.class);

        Scanner input = new Scanner(System.in);
        User user;
        Event event;

        System.out.println("--------------------- Movie Theater program ---------------------");

        boolean quitFlag = false;

        while (!quitFlag) {
            showMenu();
            System.out.print("Enter choice [1-14]: ");
            int choiceMainMenu = Integer.parseInt(input.nextLine());
            ;
            switch (choiceMainMenu) {
                case 1:
                    user = userOperations.getUserByEmail(userService, input);
                    System.out.println(user);
                    break;
                case 2:
                    userOperations.removeUser(userService, input);
                    break;
                case 3:
                    user = userOperations.getUserById(userService, input);
                    System.out.println(user);
                    break;
                case 4:
                    userOperations.addUser(userService, input);
                    break;
                case 5:
                    userOperations.printUsers(userService.getAll());
                    break;
                case 6:
                    event = eventOperations.getEventByName(eventService, input);
                    break;
                case 7:
                    eventOperations.removeEvent(eventService, input);
                    break;
                case 8:
                    event = eventOperations.getEventById(eventService, input);
                    break;
                case 9:
                    eventOperations.addEvent(eventService, auditoriumService, input);
                    break;
                case 10:
                    eventOperations.printEvents(eventService.getAll());
                    break;
                case 11:
                    eventOperations.getNextEvents(eventService, input);
                    break;
                case 12:
                    eventOperations.getEventsForDateRange(eventService, input);
                    break;
                case 14:
                    System.out.println("Thank you for using Movie Theater program");
                    quitFlag = true;
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }


}
