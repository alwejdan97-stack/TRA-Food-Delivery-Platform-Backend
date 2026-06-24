package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RestaurantRequestDTO {
    @NotNull
    private Integer id;
    @NotNull
    private Integer ownerId;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @NotBlank(message = "Cuisine Type Can't Be Empty")
    private String cuisineType;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @DecimalMin("0.0")
    private double DeliveryFee;
    @DecimalMin("0.0")
    private double minOrderAmount;
    private Boolean acceptingOrders;

    public RestaurantRequestDTO applyToEntity(Restaurant entity){
        RestaurantRequestDTO dto = new RestaurantRequestDTO();
        dto.setId(entity.getId());
        dto.setOwnerId(entity.getRestaurantOwner().getId());
        dto.setName(dto.getName());
        dto.setDescription(entity.getDescription());
        dto.setOpeningTime(entity.getOpeningTime());
        dto.setDeliveryFee(entity.getDeliveryFee());
        dto.setClosingTime(entity.getClosingTime());
        dto.setCuisineType(entity.getCuisineType());
        dto.setMinOrderAmount(entity.getMinOrderAmount());
        dto.setAcceptingOrders(entity.getAcceptingOrders());
        return dto;
    }
}
