package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentDTOSummary {
    @NotBlank(message = "Payment Method Can't Be Empty")
    private String paymentMethod;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;
    @DecimalMin("0.0")
    private Double amount;
    private LocalDateTime processedAt;

    public static PaymentDTOSummary fromEntity(Payment entity) {
        PaymentDTOSummary dto = new PaymentDTOSummary();
        dto.setPaymentMethod(dto.getPaymentMethod());
        dto.setStatus(entity.getStatus());
        dto.setAmount(entity.getAmount());
        dto.setProcessedAt(entity.getProcessedAt());
        return dto;
    }
}
