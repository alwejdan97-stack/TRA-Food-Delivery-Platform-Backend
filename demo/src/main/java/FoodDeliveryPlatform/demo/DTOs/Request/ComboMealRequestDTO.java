package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComboMealRequestDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String comboName;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private double totalPrice;

    public void applyToEntity(ComboMeal entity){
        entity.setId(this.getId());
        entity.setComboName(this.comboName);
        entity.setDescription(this.description);
        entity.setTotalPrice(this.totalPrice);
    }
}
