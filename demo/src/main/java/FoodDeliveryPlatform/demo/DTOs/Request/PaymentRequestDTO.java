package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Payment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Payment Method Can't Be Empty")
    private String paymentMethod;
    @Pattern(regexp = "YES|...| NO")
    private String status;
    @DecimalMin("0.0")
    private double amount;
    private LocalDateTime processedAt;

    public void applyToEntity(Payment entity){
        entity.setId(this.getId());
        entity.setPaymentMethod(this.paymentMethod);
        entity.setStatus(this.status);
        entity.setAmount(this.amount);
        entity.setProcessedAt(this.processedAt);
    }
}
