package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeliveryDTOSummary {
    @NotBlank(message = "Code Can't Be Empty")
    private String trackingCode;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private boolean status;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    public static DeliveryDTOSummary fromEntity(Delivery entity) {
        DeliveryDTOSummary dto = new DeliveryDTOSummary();
        dto.setTrackingCode(entity.getTrackingCode());
        dto.setStatus(entity.getStatus());
        dto.setAssignedAt(entity.getAssignedAt());
        dto.setPickedUpAt(entity.getPickedUpAt());
        dto.setDeliveredAt(entity.getDeliveredAt());
        return dto;
    }
}
