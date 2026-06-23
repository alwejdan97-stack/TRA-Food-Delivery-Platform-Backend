package FoodDeliveryPlatform.demo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDate timestamp;
    private Integer status;
    private HttpStatus httpStatus;
    private String error;
    private String message;
    private String path;
    private Map<String,String> fieldErrors;
}
