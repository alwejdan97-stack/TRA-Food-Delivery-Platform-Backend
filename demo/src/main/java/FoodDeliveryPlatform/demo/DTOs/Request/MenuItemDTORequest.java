package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.MenuItem;
import FoodDeliveryPlatform.demo.Entities.OrderItem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuItemDTORequest {
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @DecimalMin("0.0")
    private Double price;
    @Pattern(regexp = "YES|...| NO")
    private Boolean isAvailable;
    @Pattern(regexp = "YES|...| NO")
    private Boolean isVegetarian;

    public void applyToEntity(MenuItem entity){
        entity.setName(this.name);
        entity.setPrice(this.price);
        entity.setIsAvailable(this.isAvailable);
        entity.setIsVegetarian(this.isVegetarian);
    }
}
