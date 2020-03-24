package movie.theater.service;

import movie.theater.domain.Auditorium;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import java.util.Set;

public interface AuditoriumService {

    public Set<Auditorium> getAll();

    public Auditorium getByName(@Nonnull String name) throws BusinessException;

}
