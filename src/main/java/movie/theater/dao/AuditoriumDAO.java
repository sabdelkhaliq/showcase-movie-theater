package movie.theater.dao;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import movie.theater.domain.Auditorium;
import movie.theater.exception.BusinessException;

/**
 * @author Yuriy_Tkach
 */
public interface AuditoriumDAO {

    /**
     * Getting all auditoriums from the system
     *
     * @return set of all auditoriums
     */
    public @Nonnull
    Set<Auditorium> getAll();

    /**
     * Finding auditorium by name
     *
     * @param name Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
    public @Nullable
    Auditorium getByName(@Nonnull String name) throws BusinessException;

}
