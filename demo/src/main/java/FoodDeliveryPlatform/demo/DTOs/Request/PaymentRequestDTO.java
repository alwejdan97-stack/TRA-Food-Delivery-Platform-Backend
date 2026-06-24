package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Payment;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotBlank(message = "Payment Method Can't Be Empty")
    private String paymentMethod;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private boolean status;
    @DecimalMin("0.0")
    private double amount;
    private LocalDateTime processedAt;

    public void applyToEntity(Payment entity){
        entity.setPaymentMethod(this.paymentMethod);
        entity.setStatus(this.status);
        entity.setAmount(this.amount);
        entity.setProcessedAt(this.processedAt);
    }
}
