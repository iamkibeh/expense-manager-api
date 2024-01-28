package tech.kibetimmanuel.expensemanagerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ItemExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    public ItemExistsException(String message) {
        super(message);
    }
}
