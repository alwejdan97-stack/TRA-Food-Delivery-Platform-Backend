package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
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
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @Min(1)
    private double minOrderAmount;
    @Pattern(regexp = "YES|...| NO")
    private boolean acceptingOrders;

    public static RestaurantDTOSummary fromEntity(Restaurant entity) {
        RestaurantDTOSummary dto = new RestaurantDTOSummary();
        dto.setName(dto.getName());
        dto.setDescription(entity.getDescription());
        dto.setOpeningTime(entity.getOpeningTime());
        dto.setClosingTime(entity.getClosingTime());
        dto.setMinOrderAmount(entity.getMinOrderAmount());
        dto.setAcceptingOrders(entity.getAcceptingOrders());
        return dto;
    }
}
