package atm.check.atmapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção personalizada lançada quando um recurso (entidade) não é encontrado
 * na base de dados.
 * Mapeia automaticamente para o código de status HTTP 404 (Not Found)
 * devido à anotação @ResponseStatus.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
