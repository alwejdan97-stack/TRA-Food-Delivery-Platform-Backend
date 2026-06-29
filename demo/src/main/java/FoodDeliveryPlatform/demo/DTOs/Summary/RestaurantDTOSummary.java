package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RestaurantDTOSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @NotBlank(message = "Cuisine Type Can't Be Empty")
    private String cuisineType;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @Min(1)
    private double minOrderAmount;
    @Pattern(regexp = "YES|...| NO")
    private Boolean acceptingOrders;

    public static RestaurantDTOSummary fromEntity(Restaurant entity) {
        RestaurantDTOSummary dto = new RestaurantDTOSummary();
        dto.setName(dto.getName());
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setOpeningTime(entity.getOpeningTime());
        dto.setCuisineType(entity.getCuisineType());
        dto.setClosingTime(entity.getClosingTime());
        dto.setMinOrderAmount(entity.getMinOrderAmount());
        dto.setAcceptingOrders(entity.getAcceptingOrders());
        return dto;
    }
}
