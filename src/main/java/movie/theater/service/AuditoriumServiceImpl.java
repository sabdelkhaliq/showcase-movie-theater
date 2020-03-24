package movie.theater.service;

import movie.theater.dao.AuditoriumDAO;
import movie.theater.domain.Auditorium;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {
    AuditoriumDAO auditoriumDAO;

    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDAO.getAll();
    }

    @Override
    public Auditorium getByName(@Nonnull String name) throws BusinessException {
        return auditoriumDAO.getByName(name);
    }

    public AuditoriumDAO getAuditoriumDAO() {
        return auditoriumDAO;
    }

    public void setAuditoriumDAO(AuditoriumDAO auditoriumDAO) {
        this.auditoriumDAO = auditoriumDAO;
    }
}
