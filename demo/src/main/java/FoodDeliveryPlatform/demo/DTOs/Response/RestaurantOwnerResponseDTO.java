package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantOwnerResponseDTO extends PersonDTO {
    public static RestaurantOwnerResponseDTO convertToDTO(RestaurantOwner entity) {
        RestaurantOwnerResponseDTO dto = new RestaurantOwnerResponseDTO();
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
    public static List<RestaurantOwnerResponseDTO> convertToDTO(List<RestaurantOwner> entities) {
        List<RestaurantOwnerResponseDTO> dtos = new ArrayList<>();
        for (RestaurantOwner entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
