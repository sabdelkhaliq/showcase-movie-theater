package movie.theater.dao;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import movie.theater.domain.Event;
import movie.theater.exception.BusinessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
public interface EventDAO extends AbstractDomainObjectDAO<Event> {

    /**
     * Finding event by name
     * 
     * @param name
     *            Name of the event
     * @return found event or <code>null</code>
     */
    public @Nullable Event getByName(@Nonnull String name) throws BusinessException;

    /*
     * Finding all events that air on specified date range
     * 
     * @param from Start date
     * 
     * @param to End date inclusive
     * 
     * @return Set of events
     */
     public @Nonnull
     Set<Event> getForDateRange(@Nonnull LocalDate from,
                                @Nonnull LocalDate to) throws BusinessException;

    /*
     * Return events from 'now' till the the specified date time
     * 
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
     public @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to) throws BusinessException;

}
