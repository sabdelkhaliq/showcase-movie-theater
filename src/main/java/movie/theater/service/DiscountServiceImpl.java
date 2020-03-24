package movie.theater.service;

import movie.theater.domain.Event;
import movie.theater.domain.User;
import movie.theater.service.discount.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    List<DiscountStrategy> discountStrategyList;

    public double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        double birthdayTicketDiscount = discountStrategyList.get(0).getDiscount(user, airDateTime, numberOfTickets);
        double tenthTicketDiscount = discountStrategyList.get(1).getDiscount(user, airDateTime, numberOfTickets);
        return (birthdayTicketDiscount > tenthTicketDiscount) ? birthdayTicketDiscount : tenthTicketDiscount;
    }

    public List<DiscountStrategy> getDiscountStrategyList() {
        return discountStrategyList;
    }

    public void setDiscountStrategyList(List<DiscountStrategy> discountStrategyList) {
        this.discountStrategyList = discountStrategyList;
    }
}
