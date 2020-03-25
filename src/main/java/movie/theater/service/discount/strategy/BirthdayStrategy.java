package movie.theater.service.discount.strategy;

import movie.theater.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class BirthdayStrategy implements DiscountStrategy {
    @Override
    public double getDiscount(User user, LocalDateTime localDate) {
        if (user.getBirthdate() != null && DAYS.between(user.getBirthdate(), localDate) <= 5)
            return 5;
        else
            return 0;
    }
}
