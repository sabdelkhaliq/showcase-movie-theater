package movie.theater.service;

import movie.theater.domain.Event;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public interface EventService {

    public Event getByName(@Nonnull String name) throws BusinessException;

    public Event save(@Nonnull Event event) throws BusinessException;

    public void remove(@Nonnull Event event) throws BusinessException;

    public Event getById(@Nonnull Long id) throws BusinessException;

    public Collection<Event> getAll();

    public @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDate from,
                               @Nonnull LocalDate to) throws BusinessException;

    public @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDateTime to) throws BusinessException;

}
