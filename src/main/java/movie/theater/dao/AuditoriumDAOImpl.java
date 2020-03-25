package movie.theater.dao;

import movie.theater.exception.BusinessException;
import movie.theater.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AuditoriumDAOImpl implements AuditoriumDAO {
    Map<Integer, Auditorium> auditoriums;

    public AuditoriumDAOImpl() throws FileNotFoundException {
        auditoriums = new HashMap<>();
        Set<Long> vipSeats = new HashSet<>();
        vipSeats.add(10L);
        vipSeats.add(15L);
        auditoriums.put(1, new Auditorium("Auditorium 1", 100, vipSeats));
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriums.values().stream().collect(Collectors.toCollection(HashSet::new));

    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) throws BusinessException {
        if (name == null)
            throw new BusinessException("Auditorium name shouldn't be null");
        return auditoriums.values().stream().filter(auditorium -> name.equalsIgnoreCase(auditorium.getName())).findFirst().orElseThrow(() -> new BusinessException("Auditorium name not found"));
    }

    public Map<Integer, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(Map<Integer, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
