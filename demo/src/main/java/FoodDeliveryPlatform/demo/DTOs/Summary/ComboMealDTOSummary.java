package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComboMealDTOSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String comboName;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private double totalPrice;

    public static ComboMealDTOSummary fromEntity(ComboMeal entity) {
        ComboMealDTOSummary dto = new ComboMealDTOSummary();
        dto.setId(entity.getId());
        dto.setComboName(entity.getComboName());
        dto.setDescription(entity.getDescription());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }
}
