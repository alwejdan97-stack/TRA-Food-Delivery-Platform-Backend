package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.MenuItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuItemRequestDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @DecimalMin("0.0")
    private double price;
    @Pattern(regexp = "YES|...| NO")
    private Boolean isAvailable;
    @Pattern(regexp = "YES|...| NO")
    private Boolean isVegetarian;

    public void applyToEntity(MenuItem entity){
        entity.setId(this.getId());
        entity.setName(this.name);
        entity.setPrice(this.price);
        entity.setIsAvailable(this.isAvailable);
        entity.setIsVegetarian(this.isVegetarian);
    }
}
