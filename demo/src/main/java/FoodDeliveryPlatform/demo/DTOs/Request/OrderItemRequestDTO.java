package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.OrderItem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequestDTO {
    @PositiveOrZero
    private Integer quantity;
    @DecimalMin("0.0")
    private Double unitPrice;
    @DecimalMin("0.0")
    private Double totalPrice;

    public void applyToEntity(OrderItem entity){
        entity.setQuantity(this.quantity);
        entity.setUnitPrice(this.unitPrice);
        entity.setTotalPrice(this.totalPrice);
    }
}
