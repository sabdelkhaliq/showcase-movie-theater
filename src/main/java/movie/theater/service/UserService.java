package movie.theater.service;

import movie.theater.domain.User;
import movie.theater.exception.BusinessException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public interface UserService {

    public User getUserByEmail(@Nonnull String email) throws BusinessException;

    public User save(@Nonnull User user) throws BusinessException;

    public void remove(@Nonnull User user) throws BusinessException;

    public User getById(@Nonnull Long id) throws BusinessException;

    public Collection<User> getAll();
}
