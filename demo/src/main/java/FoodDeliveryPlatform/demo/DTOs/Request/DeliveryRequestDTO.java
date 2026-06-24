package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Delivery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeliveryRequestDTO {
    @NotBlank(message = "Code Can't Be Empty")
    private String trackingCode;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private boolean status;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;

    public void applyToEntity(Delivery entity){
        entity.setTrackingCode(this.trackingCode);
        entity.setStatus(this.status);
        entity.setAssignedAt(this.assignedAt);
        entity.setPickedUpAt(this.pickedUpAt);
        entity.setDeliveredAt(this.deliveredAt);
    }
}
