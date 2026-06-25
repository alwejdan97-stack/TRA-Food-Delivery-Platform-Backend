package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.OrderItemDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.PaymentDTOSummary;
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
public class PaymentResponseDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Payment Method Can't Be Empty")
    private String paymentMethod;
    @Pattern(regexp = "YES|...| NO")
    private String status;
    @DecimalMin("0.0")
    private double amount;
    private LocalDateTime processedAt;
    private PaymentDTOSummary paymentDTOSummary;

    public static PaymentResponseDTO convertToDTO(Payment entity) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(entity.getId());
        dto.setPaymentMethod(dto.getPaymentMethod());
        dto.setStatus(entity.getStatus());
        dto.setAmount(entity.getAmount());
        dto.setProcessedAt(entity.getProcessedAt());

        PaymentDTOSummary summary=new PaymentDTOSummary();
        summary.setAmount(entity.getAmount());
        summary.setPaymentMethod(entity.getPaymentMethod());
        summary.setProcessedAt(entity.getProcessedAt());
        summary.setAmount(entity.getAmount());

        dto.setPaymentDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<PaymentResponseDTO> convertToDTO(List<Payment> entities) {
        List<PaymentResponseDTO> dtos = new ArrayList<>();
        for (Payment entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
