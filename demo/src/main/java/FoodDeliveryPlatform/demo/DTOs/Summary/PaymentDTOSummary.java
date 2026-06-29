package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Payment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentDTOSummary {
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

    public static PaymentDTOSummary fromEntity(Payment entity) {
        PaymentDTOSummary dto = new PaymentDTOSummary();
        dto.setPaymentMethod(dto.getPaymentMethod());
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setAmount(entity.getAmount());
        dto.setProcessedAt(entity.getProcessedAt());
        return dto;
    }
}
