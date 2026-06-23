package FoodDeliveryPlatform.demo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDate timestamp;
    private Integer httpStatus;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus httpStatus, int value, String internalServerError, String s, String replace) {
    }
}
