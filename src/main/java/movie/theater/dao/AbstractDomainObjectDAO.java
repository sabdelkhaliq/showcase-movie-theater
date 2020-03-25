package movie.theater.dao;

import java.util.Collection;

import javax.annotation.Nonnull;

import movie.theater.domain.DomainObject;
import movie.theater.exception.BusinessException;

public interface AbstractDomainObjectDAO<T extends DomainObject> {

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object Object to save
     * @return saved object with assigned id
     */
    public T save(@Nonnull T object) throws BusinessException;

    /**
     * Removing object from storage
     *
     * @param object Object to remove
     */
    public void remove(@Nonnull T object) throws BusinessException;

    /**
     * Getting object by id from storage
     *
     * @param id id of the object
     * @return Found object or <code>null</code>
     */
    public T getById(@Nonnull Long id) throws BusinessException;

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public @Nonnull
    Collection<T> getAll();
}
