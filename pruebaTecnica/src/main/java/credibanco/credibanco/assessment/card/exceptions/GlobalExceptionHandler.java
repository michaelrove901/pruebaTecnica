package credibanco.credibanco.assessment.card.exceptions;

import credibanco.credibanco.assessment.card.exceptions.exceptions.CodigoTarjetaException;
import credibanco.credibanco.assessment.card.exceptions.exceptions.CodigoTransaccionNotFound;
import credibanco.credibanco.assessment.card.exceptions.exceptions.TarjetaNotFoundException;
import credibanco.credibanco.assessment.card.exceptions.exceptions.TarjetaValidaException;
import credibanco.credibanco.assessment.card.exceptions.response.ErrorDetallesDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(TarjetaNotFoundException.class)
    public ResponseEntity<ErrorDetallesDTO> TargetaNotFoundHandle(TarjetaNotFoundException e, WebRequest webRequest) {
        HttpStatus estado = HttpStatus.NOT_FOUND;
        ErrorDetallesDTO errorDetallesDTO = new ErrorDetallesDTO(String.valueOf(estado.value()), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetallesDTO, estado);
    }

    @ExceptionHandler(CodigoTransaccionNotFound.class)
    public ResponseEntity<ErrorDetallesDTO> CodigoTransaccionNotFound(CodigoTransaccionNotFound e, WebRequest webRequest) {
        HttpStatus estado = HttpStatus.NOT_FOUND;
        ErrorDetallesDTO errorDetallesDTO = new ErrorDetallesDTO(String.valueOf(estado.value()), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetallesDTO, estado);
    }
    @ExceptionHandler(TarjetaValidaException.class)
    public ResponseEntity<ErrorDetallesDTO> TarjetaValidaException(TarjetaValidaException e, WebRequest webRequest) {
        HttpStatus estado = HttpStatus.BAD_REQUEST;
        ErrorDetallesDTO errorDetallesDTO = new ErrorDetallesDTO(String.valueOf(estado.value()), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetallesDTO, estado);
    }

    @ExceptionHandler(CodigoTarjetaException.class)
    public ResponseEntity<ErrorDetallesDTO> CodigoTarjetaException(CodigoTarjetaException e, WebRequest webRequest) {
        HttpStatus estado = HttpStatus.NOT_FOUND;
        ErrorDetallesDTO errorDetallesDTO = new ErrorDetallesDTO(String.valueOf(estado.value()), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetallesDTO, estado);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream().map(el -> el.getField() + ": " + el.getDefaultMessage()).collect(Collectors.joining(", "));
        HttpStatus estado = HttpStatus.BAD_REQUEST;
        ErrorDetallesDTO errorDetallesDTO = new ErrorDetallesDTO(String.valueOf(estado.value()), message, request.getDescription(false));
        return new ResponseEntity<>(errorDetallesDTO, estado);
    }

}
