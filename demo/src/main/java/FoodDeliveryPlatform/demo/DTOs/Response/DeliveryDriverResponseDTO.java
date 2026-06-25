package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.DTOs.Summary.CustomerDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.DeliveryDriverDTOSummary;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryDriverResponseDTO extends PersonDTO {
    private DeliveryDriverDTOSummary deliveryDriverDTOSummary;
    @NotBlank(message = "Password Must Be Strong...")
    private String PasswordHash;

    public static DeliveryDriverResponseDTO convertToDTO(DeliveryDriver entity) {
        DeliveryDriverResponseDTO dto = new DeliveryDriverResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setPasswordHash(entity.getPasswordHash());

        DeliveryDriverDTOSummary summary=new DeliveryDriverDTOSummary();
        summary.setId(entity.getId());
        summary.setEmail(entity.getEmail());
        summary.setFirstName(entity.getFirstName());
        summary.setLastName(entity.getLastName());
        summary.setPhone(entity.getPhone());
        summary.setPassword(entity.getPasswordHash());

        dto.setDeliveryDriverDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<DeliveryDriverResponseDTO> convertToDTO(List<DeliveryDriver> entities) {
        List<DeliveryDriverResponseDTO> dtos = new ArrayList<>();
        for (DeliveryDriver entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
