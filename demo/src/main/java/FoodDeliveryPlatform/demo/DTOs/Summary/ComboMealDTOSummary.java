package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.DTOs.Response.ComboMealDTOResponse;
import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import FoodDeliveryPlatform.demo.Entities.CorporateOrder;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComboMealDTOSummary {
    @NotBlank(message = "Name Can't Be Empty")
    private String comboName;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private Double totalPrice;

    public static ComboMealDTOSummary fromEntity(ComboMeal entity) {
        ComboMealDTOSummary dto = new ComboMealDTOSummary();
        dto.setComboName(entity.getComboName());
        dto.setDescription(entity.getDescription());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }
}
