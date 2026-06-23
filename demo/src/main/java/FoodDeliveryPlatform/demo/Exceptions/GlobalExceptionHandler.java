package FoodDeliveryPlatform.demo.Exceptions;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends RuntimeException{
    public GlobalExceptionHandler(String message){
        super(message);
    }
}
