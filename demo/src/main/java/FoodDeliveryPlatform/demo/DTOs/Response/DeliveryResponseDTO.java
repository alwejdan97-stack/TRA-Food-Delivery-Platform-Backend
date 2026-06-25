package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.DeliveryDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.DeliveryDriverDTOSummary;
import FoodDeliveryPlatform.demo.Entities.Delivery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryResponseDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Code Can't Be Empty")
    private String trackingCode;
    @Pattern(regexp = "PENDING|...| CANCELLED |...| DELIVERED")
    private String status;
    private LocalDateTime assignedAt;
    private DeliveryDTOSummary deliveryDTOSummary;

    public static DeliveryResponseDTO convertToDTO(Delivery entity) {
        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setId(entity.getId());
        dto.setTrackingCode(entity.getTrackingCode());
        dto.setStatus(entity.getStatus());
        dto.setAssignedAt(entity.getAssignedAt());

        DeliveryDTOSummary summary=new DeliveryDTOSummary();
        summary.setDeliveredAt(entity.getDeliveredAt());
        summary.setPickedUpAt(entity.getPickedUpAt());
        summary.setStatus(entity.getStatus());
        summary.setAssignedAt(entity.getAssignedAt());
        summary.setTrackingCode(entity.getTrackingCode());

        dto.setDeliveryDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<DeliveryResponseDTO> convertToDTO(List<Delivery> entities) {
        List<DeliveryResponseDTO> dtos = new ArrayList<>();
        for (Delivery entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
