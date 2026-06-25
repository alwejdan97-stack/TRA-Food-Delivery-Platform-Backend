package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.DeliveryDriverDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.MenuItemDTOSummary;
import FoodDeliveryPlatform.demo.Entities.MenuItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MenuItemResponseDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Name Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private double price;
    @Pattern(regexp = "YES|...| NO")
    private boolean isAvailable;
    @Pattern(regexp = "YES|...| NO")
    private boolean isVegetarian;
    @DecimalMin("0.0")
    private double calories;
    private MenuItemDTOSummary menuItemDTOSummary;

    public static MenuItemResponseDTO convertToDTO(MenuItem entity) {
        MenuItemResponseDTO dto = new MenuItemResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setAvailable(entity.getIsAvailable());
        dto.setVegetarian(entity.getIsVegetarian());
        dto.setCalories(entity.getCalories());

        MenuItemDTOSummary summary=new MenuItemDTOSummary();
        summary.setName(entity.getName());
        summary.setDescription(entity.getDescription());
        summary.setIsAvailable(entity.getIsAvailable());
        summary.setPrice(entity.getPrice());
        summary.setCalories(entity.getCalories());

        dto.setMenuItemDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<MenuItemResponseDTO> convertToDTO(List<MenuItem> entities) {
        List<MenuItemResponseDTO> dtos = new ArrayList<>();
        for (MenuItem entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
