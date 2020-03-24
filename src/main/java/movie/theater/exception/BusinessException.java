package movie.theater.exception;

public class BusinessException extends RuntimeException {
    String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
