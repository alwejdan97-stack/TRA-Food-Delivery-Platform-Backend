package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Orders;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersRequestDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Code Can't Be Null")
    private String orderCode;
    @NotBlank(message = "Note Can't Be Null")
    private String deliveryNotes;
    @Pattern(regexp = "PENDING|...| CANCELLED |...| DELIVERED")
    private String status;

    public void applyToEntity(Orders entity){
        entity.setId(this.id);
        entity.setOrderCode(this.orderCode);
        entity.setStatus(this.status);
        entity.setDeliveryNotes(this.deliveryNotes);
    }
}
