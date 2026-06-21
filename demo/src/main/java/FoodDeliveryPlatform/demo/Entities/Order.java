package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String orderCode;
    private LocalDate orderDate;
    private Boolean status;
    private double subtotal;
    private double deliveryFee;
    private double discountAmount;
    private double totalAmount;
    private String deliveryNotes;

    @ManyToOne
    @Column(name = "customerID")
    private List<Customer> customers;

    @ManyToOne
    @Column(name = "restaurantID")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "Order")
    private List<OrderItem> orderItems;

    @OneToOne
    private List<Delivery> deliveries;

    @OneToOne
    private List<Payment> payments;
}