package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryDriverDTOSummary extends PersonDTO {
    public static DeliveryDriverDTOSummary fromEntity(DeliveryDriver entity) {
        DeliveryDriverDTOSummary dto = new DeliveryDriverDTOSummary();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());

        return dto;
    }
}
