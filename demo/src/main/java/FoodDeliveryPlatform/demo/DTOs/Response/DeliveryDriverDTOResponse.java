package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryDriverDTOResponse extends PersonDTO {
    public static DeliveryDriverDTOResponse convertToDTO(DeliveryDriver entity) {
        DeliveryDriverDTOResponse dto = new DeliveryDriverDTOResponse();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        //dto.setPasswordHash(entity.getPasswordHash());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<DeliveryDriverDTOResponse> convertToDTO(List<DeliveryDriver> entities) {
        List<DeliveryDriverDTOResponse> dtos = new ArrayList<>();
        for (DeliveryDriver entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
