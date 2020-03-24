package movie.theater.service;

import movie.theater.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    Map<Integer, User> users;

    public UserServiceImpl() {
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return users.values().stream().filter(user -> email.equalsIgnoreCase(user.getEmail())).findFirst().orElseThrow(() -> new RuntimeException("email not found"));
    }

    @Override
    public User save(@Nonnull User user) {
        return users.put(users.size(), user);
    }

    @Override
    public void remove(@Nonnull User user) {
        users.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return users.values().stream().filter(user -> id.equals(user.getId())).findFirst().orElseThrow(() -> new RuntimeException("id not found"));
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return users.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }
}
