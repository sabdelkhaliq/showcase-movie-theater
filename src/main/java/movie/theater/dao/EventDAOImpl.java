package movie.theater.dao;

import movie.theater.domain.Event;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventDAOImpl implements EventDAO {
    Map<Integer, Event> events;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) throws BusinessException {
        return events.values().stream().filter(user -> name.equalsIgnoreCase(user.getName())).findFirst().orElseThrow(() -> new BusinessException("Event name not found"));
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) throws BusinessException {
        if (to == null)
            throw new BusinessException("Time to cannot be null");
        return events
                .values()
                .stream()
                .filter(e -> (e.getAirDates().stream().anyMatch(d -> !d.isBefore(LocalDateTime.now()) && !d.isAfter(ChronoLocalDateTime.from(to)))))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) throws BusinessException {
        if (from == null)
            throw new BusinessException("Time from cannot be null");
        if (to == null)
            throw new BusinessException("Time to cannot be null");

        return events
                .values()
                .stream()
                .filter(e -> (e.getAirDates().stream().anyMatch(d -> !d.isBefore(ChronoLocalDateTime.from(from)) && !d.isAfter(ChronoLocalDateTime.from(to)))))
                .collect(Collectors.toCollection(HashSet::new));

    }

    @Override
    public Event save(@Nonnull Event event) throws BusinessException {
        return events.put(event.getId().intValue(), event);
    }

    @Override
    public void remove(@Nonnull Event event) throws BusinessException {
        events.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) throws BusinessException {
        return events.values().stream().filter(user -> id.equals(user.getId())).findFirst().orElseThrow(() -> new BusinessException("Event id not found"));
    }

    @Nonnull
    @Override
    public List<Event> getAll() {
        return events.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public Map<Integer, Event> getEvents() {
        return events;
    }

    public void setEvents(Map<Integer, Event> events) {
        this.events = events;
    }
}
