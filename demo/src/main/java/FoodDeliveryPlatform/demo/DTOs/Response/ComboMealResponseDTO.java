package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.ComboMealDTOSummary;
import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ComboMealResponseDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String comboName;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private double totalPrice;
    private ComboMealDTOSummary comboMealDTOSummary;

    public static ComboMealResponseDTO convertToDTO(ComboMeal entity) {
        ComboMealResponseDTO dto = new ComboMealResponseDTO();
        dto.setId(entity.getId());
        dto.setComboName(entity.getComboName());
        dto.setDescription(entity.getDescription());
        dto.setTotalPrice(entity.getTotalPrice());

        ComboMealDTOSummary summary=new ComboMealDTOSummary();
        summary.setComboName(entity.getComboName());
        summary.setDescription(entity.getDescription());
        summary.setTotalPrice(entity.getTotalPrice());

        dto.setComboMealDTOSummary(summary);

        return dto;
    }

    @NotEmpty
    @Valid
    public static List<ComboMealResponseDTO> convertToDTO(List<ComboMeal> entities) {
        List<ComboMealResponseDTO> dtos = new ArrayList<>();
        for (ComboMeal entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
