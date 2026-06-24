package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDTOSummary {
    @NotNull
    private Integer id;
    @NotBlank(message = "Code Can't Be Null")
    private String orderCode;
    @NotBlank(message = "Note Can't Be Null")
    private String deliveryNotes;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;

    public static DeliveryDriverDTOSummary fromEntity(DeliveryDriver entity) {
        DeliveryDriverDTOSummary dto = new DeliveryDriverDTOSummary();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
