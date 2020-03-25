package movie.theater.operations;

import movie.theater.domain.User;
import movie.theater.exception.BusinessException;
import movie.theater.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;

public class UserOperations {

    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final DateTimeFormatter FORMATTER_TIMESTAMP = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public void removeUser(UserService userService, Scanner input) {
        User user;
        user = getUserById(userService, input);
        if (user != null) {
            userService.remove(user);
            System.out.println("User removed");
        }
    }

    public void printUsers(Collection<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users were found");
        } else for (User user : users)
            System.out.println(user);
    }

    public User getUserById(UserService userService, Scanner input) {
        User user = null;
        try {
            System.out.println("Enter user id: ");
            Long id = input.nextLong();
            user = userService.getById(id);

        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User getUserByEmail(UserService userService, Scanner input) {
        User user = null;
        try {
            System.out.println("Enter user email: ");
            String email = input.nextLine();
            user = userService.getUserByEmail(email);
            System.out.println("User details: ");
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void addUser(UserService userService, Scanner input) {
        System.out.println("Enter first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter last name: ");
        String lastName = input.nextLine();
        System.out.println("Enter email: ");
        String email = input.nextLine();
        System.out.println("Enter birthDate [ex: dd-MM-yyyy]: ");
        String birthDate = input.nextLine();
        userService.save(new User(firstName, lastName, email, LocalDate.parse(birthDate, FORMATTER), null));
    }
}