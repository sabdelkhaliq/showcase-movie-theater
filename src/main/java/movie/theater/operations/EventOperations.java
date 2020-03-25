package movie.theater.operations;

import movie.theater.domain.Event;
import movie.theater.domain.EventRating;
import movie.theater.exception.BusinessException;
import movie.theater.service.AuditoriumService;
import movie.theater.service.EventService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class EventOperations {

    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final DateTimeFormatter FORMATTER_TIMESTAMP = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public void printEvents(Collection<Event> events) {
        if (events.isEmpty())
            System.out.println("No Events were found");
        else {
            for (Event event : events)
                System.out.println(event);
        }
    }

    public void addEvent(EventService eventService, AuditoriumService auditoriumService, Scanner input) {
        Event event = new Event();

        System.out.println("Enter event name");
        String name = input.nextLine();
        event.setName(name);

        System.out.println("Enter event base price");
        double basePrice = Double.parseDouble(input.nextLine());
        event.setBasePrice(basePrice);

        System.out.println("Enter event rating 1 for low 2 for mid 3 for high");
        int rating = Integer.parseInt(input.nextLine());


        EventRating eventRating;
        if (rating == 3)
            eventRating = EventRating.HIGH;
        else if (rating == 2)
            eventRating = EventRating.MID;
        else
            eventRating = EventRating.LOW;

        event.setRating(eventRating);

        char airDateCheck = 'y';
        do {
            System.out.println("Do you want to add air date to event (y/n)");
            airDateCheck = input.nextLine().charAt(0);
            if (airDateCheck == 'y' || airDateCheck == 'Y') {
                System.out.println("Enter air date timestamp [ex: dd-MM-yyyy HH:mi]: ");
                String date = input.nextLine();
                event.addAirDateTime(LocalDateTime.parse(date, FORMATTER_TIMESTAMP));
            }
        } while (airDateCheck == 'y');

        eventService.save(event);

    }

    public void removeEvent(EventService eventService, Scanner input) {
        Event event;
        event = getEventById(eventService, input);
        if (event != null) {
            eventService.remove(event);
            System.out.println("Event removed");
        }
    }

    public Event getEventById(EventService eventService, Scanner input) {
        Event event = null;
        try {
            System.out.println("Enter event id: ");
            Long id = input.nextLong();
            event = eventService.getById(id);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
        return event;
    }

    public Event getEventByName(EventService eventService, Scanner input) {
        Event event = null;
        try {
            System.out.println("Enter event name: ");
            String name = input.nextLine();
            event = eventService.getByName(name);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
        return event;
    }

    public void getEventsForDateRange(EventService eventService, Scanner input) {
        try {
            System.out.println("Enter from date [ex: dd-MM-yyyy]: ");
            String from = input.nextLine();
            System.out.println("Enter to date [ex: dd-MM-yyyy]: ");
            String to = input.nextLine();

            Set<Event> events = eventService.getForDateRange(LocalDate.parse(from, FORMATTER), LocalDate.parse(to, FORMATTER));

            printEvents(events);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getNextEvents(EventService eventService, Scanner input) {
        try {
            System.out.println("Enter date timestamp [ex: dd-MM-yyyy HH:mi]: ");
            String date = input.nextLine();

            Set<Event> events = eventService.getNextEvents(LocalDateTime.parse(date, FORMATTER_TIMESTAMP));

            printEvents(events);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }
}