package repository;

import java.util.Collection;
import java.util.Optional;

/**
 * A generic interface for basic CRUD operations on a repository.
 *
 * @param <T>  the type of entity to be managed by the repository
 * @param <ID> the type of the entity's identifier
 */
public interface CrudRepository<T, ID> {

    /**
     * Adds an entity to the repository.
     *
     * @param entity the entity to add
     */
    void add(T entity);

    /**
     * Updates an existing entity in the repository.
     *
     * @param id     the identifier of the entity to update
     * @param entity the updated entity
     */
    void update(ID id, T entity);

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id the identifier of the entity
     * @return an Optional containing the entity if found, otherwise empty
     */
    Optional<T> get(ID id);

    /**
     * Retrieves all entities in the repository.
     *
     * @return a collection of all entities
     */
    Collection<T> getAll();

    /**
     * Deletes an entity from the repository.
     *
     * @param id the identifier of the entity to delete
     */
    void delete(ID id);
}
