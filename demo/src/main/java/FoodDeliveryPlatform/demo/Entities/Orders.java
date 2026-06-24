package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends BaseEntity{
    private String orderCode;
    private LocalDate orderDate;
    private Boolean status;
    private double subtotal;
    private double deliveryFee;
    private double discountAmount;
    private double totalAmount;
    private String deliveryNotes;

    @ManyToOne
    private Customer customer;

    @ManyToOne

    private Restaurant restaurant;

    @OneToMany()
    private List<OrderItem> orderItems;

    @OneToOne()
    private Delivery delivery;

    @OneToOne()
    private Payment payment;
}