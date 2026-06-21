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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String specialInstructions;

    @OneToMany(mappedBy = "OrderItem")
    private List<CorporateOrder> corporateOrders;

    @OneToMany(mappedBy = "OrderItem")
    private List<MenuItem> menuItems;
}
