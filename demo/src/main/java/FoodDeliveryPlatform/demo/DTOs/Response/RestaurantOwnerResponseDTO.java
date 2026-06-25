package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.DTOs.Summary.PaymentDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.RestaurantOwnerSummary;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantOwnerResponseDTO extends PersonDTO {
    @NotBlank(message = "Password Must Be Strong...")
    private String passwordHash;
    private RestaurantOwnerSummary restaurantOwnerSummary;

    public static RestaurantOwnerResponseDTO convertToDTO(RestaurantOwner entity) {
        RestaurantOwnerResponseDTO dto = new RestaurantOwnerResponseDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setPasswordHash(entity.getPasswordHash());

        RestaurantOwnerSummary summary=new RestaurantOwnerSummary();
        summary.setId(entity.getId());
        summary.setPassword(entity.getPasswordHash());
        summary.setEmail(entity.getEmail());
        summary.setPhone(entity.getPhone());
        summary.setFirstName(entity.getFirstName());
        summary.setLastName(entity.getLastName());

        dto.setRestaurantOwnerSummary(summary);
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
