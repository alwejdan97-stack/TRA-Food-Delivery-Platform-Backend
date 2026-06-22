package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Order extends BaseEntity{

    private String orderCode;
    private LocalDate orderDate;
    private Boolean status;
    private Double subtotal;
    private Double deliveryFee;
    private Double discountAmount;
    private Double totalAmount;
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