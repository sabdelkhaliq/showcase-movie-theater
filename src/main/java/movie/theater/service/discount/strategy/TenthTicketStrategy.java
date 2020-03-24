package movie.theater.service.discount.strategy;

import movie.theater.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TenthTicketStrategy implements DiscountStrategy {
    @Override
    public double getDiscount(User user, LocalDateTime localDate, long numberOfTickets) {
        if (user.getTickets().size() + numberOfTickets >= 10)
            return 50;
        else
            return 0;
    }
}
