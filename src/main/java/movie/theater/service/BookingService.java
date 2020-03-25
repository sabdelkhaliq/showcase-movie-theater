package movie.theater.service;

import movie.theater.domain.Event;
import movie.theater.domain.Ticket;
import movie.theater.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {
    /**
     * Getting price when buying all supplied seats for particular event
     *
     * @param event    Event to get base ticket price, vip seats and other
     *                 information
     * @param dateTime Date and time of event air
     * @param user     User that buys ticket could be needed to calculate discount.
     *                 Can be <code>null</code>
     * @param seats    Set of seat numbers that user wants to buy
     * @return total price
     */
    public double getTicketPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user
            , @Nonnull Long seat, boolean isVip);

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     *
     * @param tickets Set of tickets
     */
    public void bookTickets(@Nonnull Set<Ticket> tickets);

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param event    Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     */
    public @Nonnull
    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);

}
