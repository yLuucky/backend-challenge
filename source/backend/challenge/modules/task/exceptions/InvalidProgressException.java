package backend.challenge.modules.task.exceptions;

public class InvalidProgressException extends RuntimeException {

        public InvalidProgressException(String message) {
            super(message);
        }

        public InvalidProgressException() {
        }
}
