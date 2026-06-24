package FoodDeliveryPlatform.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        HttpStatus status=HttpStatus.BAD_REQUEST;
        String path=request.getDescription(false).replace("uri=", "");
        ErrorResponse errorDetails = new ErrorResponse(LocalDate.now(),
                status.value(),status,status.getReasonPhrase(),
                "An unexpected error occurred on the server.",
                path,null);
        return new ResponseEntity<>(errorDetails,status);
    }

    /*@ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException exception, WebRequest request) {
        HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
        String path=request.getDescription(false).replace("uri=", "");
        ErrorResponse errorDetails = new ErrorResponse(LocalDate.now(),
                status.value(),status,status.getReasonPhrase(), exception.getMessage(), path,null);
        return new ResponseEntity<>(errorDetails,status);
    }*/

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        HttpStatus status=HttpStatus.NOT_FOUND;
        String path=request.getDescription(false).replace("uri=", "");
        ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), status.value(), status,
                status.getReasonPhrase(), exception.getMessage(), path, null);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex, WebRequest request) {
        HttpStatus status=HttpStatus.CONFLICT;
        String path=request.getDescription(false).replace("uri=", "");
        ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), status.value(), status,
                status.getReasonPhrase(), ex.getMessage(), path, null);
        return new ResponseEntity<>(errorResponse,status);
    }

    @ExceptionHandler(InvalidOrderStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderStateException(InvalidOrderStateException exception, WebRequest request) {
        String path=request.getDescription(false).replace("uri=", "");
        HttpStatus status=HttpStatus.CONFLICT;
        ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), status.value(), status,
                status.getReasonPhrase(), exception.getMessage(), path, null);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        HttpStatus status=HttpStatus.BAD_REQUEST;
        String path=request.getDescription(false).replace("uri=", "");
        Map<String,String> fieldErrors=new HashMap<>();
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            fieldErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), status.value(), status,
                status.getReasonPhrase(), "Validation FAILED", path, fieldErrors);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
