package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String specialInstructions;

    @ManyToOne
    private CorporateOrder corporateOrder;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private MenuItem menuItem;
}
