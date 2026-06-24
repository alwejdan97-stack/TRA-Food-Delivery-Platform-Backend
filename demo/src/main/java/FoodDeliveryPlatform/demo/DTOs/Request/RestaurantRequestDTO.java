package FoodDeliveryPlatform.demo.DTOs.Request;

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
public class RestaurantRequestDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @Min(1)
    private double minOrderAmount;
    @Pattern(regexp = "YES|...| NO")
    private Boolean acceptingOrders;

    public void applyToEntity(Restaurant entity){
        entity.setId(this.getId());
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setOpeningTime(this.openingTime);
        entity.setClosingTime(this.closingTime);
        entity.setMinOrderAmount(this.minOrderAmount);
        entity.setAcceptingOrders(this.acceptingOrders);
    }
}
