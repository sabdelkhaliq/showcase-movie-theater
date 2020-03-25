package movie.theater.dao;

import movie.theater.domain.User;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDAOImpl implements UserDAO {
    Map<Integer, User> users;

    public UserDAOImpl() {
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) throws BusinessException {
        return users.values().stream().filter(user -> email.equalsIgnoreCase(user.getEmail())).findFirst().orElseThrow(() -> new BusinessException("User email not found"));
    }

    @Override
    public User save(@Nonnull User user) throws BusinessException {
        if (user.getId() == null)
            throw new BusinessException("User has invalid id value");
        return users.put(user.getId().intValue(), user);
    }

    @Override
    public void remove(@Nonnull User user) throws BusinessException {
        if (users.get(user) == null)
            throw new BusinessException("User not found");
        users.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) throws BusinessException {
        return users.values().stream().filter(user -> id.equals(user.getId())).findFirst().orElseThrow(() -> new BusinessException("User id not found"));
    }

    @Nonnull
    @Override
    public List<User> getAll() {
        return users.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }
}
