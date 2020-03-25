package movie.theater.service;

import movie.theater.domain.Event;
import movie.theater.domain.Ticket;
import movie.theater.domain.User;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingServiceImpl implements BookingService {
    DiscountService discountService;
    UserService userService;

    @Override
    public double getTicketPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Long seat, boolean isVip) {
        double discount = discountService.getDiscount(user, event, dateTime);
        return (isVip) ? discount * event.getBasePrice() * 2 : discount * event.getBasePrice();
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

        Stream<Ticket> ticketsStream = tickets.stream();
        if (!ticketsStream.allMatch(t -> t.getEvent() != null))
            throw new BusinessException("Ticket should contain event");
        if (!ticketsStream.allMatch(t -> t.getUser() != null))
            throw new BusinessException("Ticket should contain user");
        if (!ticketsStream.allMatch(t -> t.getSeat() != 0L))
            throw new BusinessException("Ticket should contain seat");
        if (!ticketsStream.allMatch(t -> t.getDateTime() != null))
            throw new BusinessException("Ticket should contain air dateTime");
        if (!ticketsStream.allMatch(t -> userService.getById(t.getUser().getId()) != null))
            throw new BusinessException("User should be registered");

        for (Ticket ticket : tickets) {
            User user = userService.getById(ticket.getUser().getId());
            NavigableSet<Ticket> userTickets = user.getTickets();

            if (userTickets == null)
                userTickets = new TreeSet<>();

            userTickets.add(ticket);
            user.setTickets(userTickets);
            userService.save(user);
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        List<User> users;
        Set<Ticket> tickets = new HashSet<>();

        users = userService.getAll().stream().collect(Collectors.toCollection(ArrayList::new));

        users.stream().filter(u -> u.getTickets().stream().anyMatch(t -> t.getEvent().getId().equals(event.getId()))).collect(Collectors.toCollection(HashSet::new));
        for (User u : users) {
            NavigableSet<Ticket> userTickets = u.getTickets();
            if (userTickets != null)
                for (Ticket t : userTickets) {
                    if (t.getEvent().getId().equals(event.getId()))
                        tickets.add(t);
                }
        }
        return tickets;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
