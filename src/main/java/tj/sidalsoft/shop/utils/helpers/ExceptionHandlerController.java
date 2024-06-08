package tj.sidalsoft.shop.utils.helpers;

import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {

        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation error");

        dto.setDetailedMessages(ex.getBindingResult().getAllErrors().stream()
                                  .map(err -> err.unwrap(ConstraintViolation.class))
                                  .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
                                  .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeException(RuntimeException ex) {

        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @Data
    public static class ErrorDto {

        private final int status;
        private final String error;
        private final String message;
        private List<String> detailedMessages;

        public ErrorDto(HttpStatus httpStatus, String message) {
            status = httpStatus.value();
            error = httpStatus.getReasonPhrase();
            this.message = message;
        }

    }
}