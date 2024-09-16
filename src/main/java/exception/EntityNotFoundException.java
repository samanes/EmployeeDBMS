package exception;

/**
 * Exception thrown when an entity is not found in the repository.
 */
public class EntityNotFoundException extends RepositoryException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
