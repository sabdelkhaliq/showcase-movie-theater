package movie.theater.service;

import movie.theater.dao.UserDAO;
import movie.theater.domain.User;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import java.util.Collection;

public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Override
    public User getUserByEmail(@Nonnull String email) throws BusinessException {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User user) throws BusinessException {
        return userDAO.save(user);
    }

    @Override
    public void remove(@Nonnull User user) throws BusinessException {
        userDAO.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) throws BusinessException {
        return userDAO.getById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
