package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.OrderItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTOSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @PositiveOrZero
    private Integer quantity;
    @DecimalMin("0.0")
    private double unitPrice;
    @DecimalMin("0.0")
    private double totalPrice;
    @NotBlank(message = "Instructions Can't Be Empty")
    private String specialInstructions;

    public static OrderItemDTOSummary fromEntity(OrderItem entity) {
        OrderItemDTOSummary dto = new OrderItemDTOSummary();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setSpecialInstructions(entity.getSpecialInstructions());
        return dto;
    }
}
