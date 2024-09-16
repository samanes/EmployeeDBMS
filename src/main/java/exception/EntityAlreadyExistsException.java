package exception;

/**
 * Exception thrown when attempting to add an entity that already exists in the repository.
 */
public class EntityAlreadyExistsException extends RepositoryException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
