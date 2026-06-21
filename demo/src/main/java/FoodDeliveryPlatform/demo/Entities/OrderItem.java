package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderItem")
public class OrderItem extends BaseEntity{
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private String specialInstructions;

    @OneToMany(mappedBy = "OrderItem")
    private List<CorporateOrder> corporateOrders;

    @OneToMany(mappedBy = "OrderItem")
    private List<MenuItem> menuItems;
}
