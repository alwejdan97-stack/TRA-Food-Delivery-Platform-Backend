package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String specialInstructions;

    @ManyToOne
    private CorporateOrder corporateOrder;

   /* @ManyToOne
    private Orders orders;*/

    @ManyToOne
    private MenuItem menuItem;
}
