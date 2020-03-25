package movie.theater.operations;

import movie.theater.domain.Auditorium;
import movie.theater.domain.Event;
import movie.theater.domain.Ticket;
import movie.theater.domain.User;
import movie.theater.service.BookingService;
import movie.theater.service.EventService;
import movie.theater.service.UserService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class BookingOperations {

    EventService eventService;
    UserService userService;
    BookingService bookingService;

    public void book(Scanner input) {
        char choice;
        Set<Ticket> tickets = new HashSet<>();

        do {
            System.out.println("1- Choose the user who want to book the ticket for");
            List<User> users = userService.getAll();

            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + " - " + users.get(i).getFirstName() + " " + users.get(i).getLastName());
            }

            User user = users.get(Integer.parseInt(input.nextLine()));

            System.out.println("2- Choose the event");
            List<Event> events = eventService.getAll();

            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + " - " + events.get(i).getName());
            }

            Event event = events.get(Integer.parseInt(input.nextLine()));

            System.out.println("3- Choose the event air date:");
            NavigableSet<LocalDateTime> eventSet = event.getAirDates();

            List<LocalDateTime> eventAirDatesList = eventSet.stream().collect(Collectors.toList());

            for (int i = 0; i < eventAirDatesList.size(); i++) {
                System.out.println((i + 1) + " - " + eventAirDatesList.get(i).toString());
            }

            LocalDateTime localDateTime = eventAirDatesList.get(Integer.parseInt(input.nextLine()));

            Auditorium auditorium = event.getAuditoriums().get(localDateTime);

            System.out.println("4- choose number from 1 to " + (auditorium.getNumberOfSeats() + 1));
            System.out.println("VIP seats are: ");

            Set<Long> vipSeats = auditorium.getVipSeats();
            List<Long> vipSeatsList = vipSeats.stream().collect(Collectors.toList());

            for (int i = 0; i < vipSeatsList.size(); i++) {
                System.out.println((i + 1) + " - " + vipSeatsList.get(i).toString());
            }

            Long seat = Long.parseLong(input.nextLine());
            boolean isVip = vipSeatsList.stream().anyMatch(v -> seat.equals(v));

            double price = bookingService.getTicketPrice(event, localDateTime, user, seat, isVip);

            System.out.println("Ticket price is " + price);

            Ticket ticket = new Ticket(user, event, localDateTime, seat);
            tickets.add(ticket);

            System.out.println("Do you want to add other ticket (y/n)");
            choice = input.nextLine().charAt(0);

        } while (choice == 'y' || choice == 'Y');

        System.out.println("Booking tickets...");
        bookingService.bookTickets(tickets);
        System.out.println("Done");
    }

    public void getPurcasheTicket(Scanner input) {
    }
}
