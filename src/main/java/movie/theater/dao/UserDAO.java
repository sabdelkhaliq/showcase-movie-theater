package movie.theater.dao;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import movie.theater.domain.User;
import movie.theater.exception.BusinessException;

/**
 * @author Yuriy_Tkach
 */
public interface UserDAO extends AbstractDomainObjectDAO<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable User getUserByEmail(@Nonnull String email)  throws BusinessException;

}
