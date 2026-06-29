package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.OrderItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequestDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @PositiveOrZero
    private int quantity;
    @DecimalMin("0.0")
    private double unitPrice;
    @DecimalMin("0.0")
    private double totalPrice;

    public void applyToEntity(OrderItem entity){
        entity.setId(this.getId());
        entity.setQuantity(this.quantity);
        entity.setUnitPrice(this.unitPrice);
        entity.setTotalPrice(this.totalPrice);
    }
}
