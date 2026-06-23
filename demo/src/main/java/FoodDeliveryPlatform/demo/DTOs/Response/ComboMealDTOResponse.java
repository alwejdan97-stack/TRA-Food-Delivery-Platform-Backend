package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.ComboMeal;
import FoodDeliveryPlatform.demo.Entities.CorporateOrder;
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
public class ComboMealDTOResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String comboName;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private Double totalPrice;

    public static ComboMealDTOResponse convertToDTO(ComboMeal entity) {
        ComboMealDTOResponse dto = new ComboMealDTOResponse();
        dto.setId(entity.getId());
        dto.setComboName(entity.getComboName());
        dto.setDescription(entity.getDescription());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<ComboMealDTOResponse> convertToDTO(List<ComboMeal> entities) {
        List<ComboMealDTOResponse> dtos = new ArrayList<>();
        for (ComboMeal entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
