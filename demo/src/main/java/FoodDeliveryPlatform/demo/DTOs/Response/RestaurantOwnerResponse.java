package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantOwnerResponse extends PersonDTO {
    public static RestaurantOwnerResponse convertToDTO(RestaurantOwner entity) {
        RestaurantOwnerResponse dto = new RestaurantOwnerResponse();
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
    public static List<RestaurantOwnerResponse> convertToDTO(List<RestaurantOwner> entities) {
        List<RestaurantOwnerResponse> dtos = new ArrayList<>();
        for (RestaurantOwner entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
