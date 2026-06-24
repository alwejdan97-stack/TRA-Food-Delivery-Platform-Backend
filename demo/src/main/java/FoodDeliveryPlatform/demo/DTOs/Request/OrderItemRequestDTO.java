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
    private int quantity;
    @DecimalMin("0.0")
    private double unitPrice;
    @DecimalMin("0.0")
    private double totalPrice;

    public void applyToEntity(OrderItem entity){
        entity.setQuantity(this.quantity);
        entity.setUnitPrice(this.unitPrice);
        entity.setTotalPrice(this.totalPrice);
    }
}
