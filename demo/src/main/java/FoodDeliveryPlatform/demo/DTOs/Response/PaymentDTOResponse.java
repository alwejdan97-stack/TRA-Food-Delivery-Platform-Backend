package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Payment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PaymentDTOResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Payment Method Can't Be Empty")
    private String paymentMethod;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;
    @DecimalMin("0.0")
    private Double amount;
    private LocalDateTime processedAt;

    public static PaymentDTOResponse convertToDTO(Payment entity) {
        PaymentDTOResponse dto = new PaymentDTOResponse();
        dto.setId(entity.getId());
        dto.setPaymentMethod(dto.getPaymentMethod());
        dto.setStatus(entity.getStatus());
        dto.setAmount(entity.getAmount());
        dto.setProcessedAt(entity.getProcessedAt());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<PaymentDTOResponse> convertToDTO(List<Payment> entities) {
        List<PaymentDTOResponse> dtos = new ArrayList<>();
        for (Payment entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
