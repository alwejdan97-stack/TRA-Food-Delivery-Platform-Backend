package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RestaurantDTORequest {
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @Min(1)
    private Double minOrderAmount;
    @Pattern(regexp = "YES|...| NO")
    private Boolean acceptingOrders;

    public void applyToEntity(Restaurant entity){
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setOpeningTime(this.openingTime);
        entity.setClosingTime(this.closingTime);
        entity.setMinOrderAmount(this.minOrderAmount);
        entity.setAcceptingOrders(this.acceptingOrders);
    }
}
