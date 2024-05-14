package backend.challenge.modules.task.exceptions.handlers;

import backend.challenge.modules.task.exceptions.InvalidProgressException;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.ExceptionHandler;
import kikaha.urouting.api.Response;

import javax.inject.Singleton;

@Singleton
public class InvalidProgressExceptionHandler implements ExceptionHandler<InvalidProgressException> {

    @Override
    public Response handle(InvalidProgressException exception) {
        return DefaultResponse.badRequest().entity("Enter a value between 1 and 100");
    }
}
