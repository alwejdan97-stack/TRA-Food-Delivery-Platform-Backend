package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.OrderItem;
import FoodDeliveryPlatform.demo.Entities.Orders;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDTORequest {
    @NotNull
    private Integer id;
    @NotBlank(message = "Code Can't Be Null")
    private String orderCode;
    @NotBlank(message = "Note Can't Be Null")
    private String deliveryNotes;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;

    public void applyToEntity(Orders entity){
        entity.setId(this.id);
        entity.setOrderCode(this.orderCode);
        entity.setStatus(this.status);
        entity.setDeliveryNotes(this.deliveryNotes);
    }
}
