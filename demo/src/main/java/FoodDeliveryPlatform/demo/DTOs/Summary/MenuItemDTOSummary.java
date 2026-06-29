package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.MenuItem;
import FoodDeliveryPlatform.demo.Entities.OrderItem;
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
public class MenuItemDTOSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Name Can't Be Empty")
    private String description;
    @DecimalMin("0.0")
    private double price;
    @Pattern(regexp = "YES|...| NO")
    private Boolean isAvailable;
    @DecimalMin("0.0")
    private double calories;

    public static MenuItemDTOSummary fromEntity(MenuItem entity) {
        MenuItemDTOSummary dto = new MenuItemDTOSummary();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setIsAvailable(entity.getIsAvailable());
        dto.setCalories(entity.getCalories());
        return dto;
    }
}
