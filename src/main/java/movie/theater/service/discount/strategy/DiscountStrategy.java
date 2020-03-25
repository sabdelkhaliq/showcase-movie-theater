package movie.theater.service.discount.strategy;

import movie.theater.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DiscountStrategy {

    public double getDiscount(User user, LocalDateTime localDate);
}