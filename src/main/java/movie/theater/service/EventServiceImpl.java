package movie.theater.service;

import movie.theater.dao.EventDAO;
import movie.theater.domain.Event;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class EventServiceImpl implements EventService {
    EventDAO eventDAO;

    @Override
    public Event getByName(@Nonnull String name) throws BusinessException {
        return eventDAO.getByName(name);
    }

    @Override
    public Event save(@Nonnull Event event) throws BusinessException {
        return eventDAO.save(event);
    }

    @Override
    public void remove(@Nonnull Event event) throws BusinessException {
        eventDAO.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) throws BusinessException {
        return eventDAO.getById(id);
    }

    @Override
    public List<Event> getAll() throws BusinessException {
        return eventDAO.getAll();
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) throws BusinessException {
        return eventDAO.getForDateRange(from, to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) throws BusinessException {
        return eventDAO.getNextEvents(to);
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }
}
