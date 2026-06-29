package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.PaymentDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.RestaurantDTOSummary;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantResponseDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    @NotNull
    private Integer ownerId;
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
    @Pattern(regexp = "YES|...| NO")
    private boolean acceptingOrders;
    private RestaurantDTOSummary restaurantDTOSummary;

    public static RestaurantResponseDTO convertToDTO(Restaurant entity) {
        RestaurantResponseDTO dto = new RestaurantResponseDTO();
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

        RestaurantDTOSummary summary=new RestaurantDTOSummary();
        summary.setName(entity.getName());
        summary.setDescription(entity.getDescription());
        summary.setCuisineType(entity.getCuisineType());
        summary.setOpeningTime(entity.getOpeningTime());
        summary.setClosingTime(entity.getClosingTime());
        summary.setMinOrderAmount(entity.getMinOrderAmount());

        dto.setRestaurantDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<RestaurantResponseDTO> convertToDTO(List<Restaurant> entities) {
        List<RestaurantResponseDTO> dtos = new ArrayList<>();
        for (Restaurant entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
