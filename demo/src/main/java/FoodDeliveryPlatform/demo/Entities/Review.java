package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String targetType;
    private String rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "restaurantID" ,nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn (name = "deliverDriverID",nullable = false)
    private DeliveryDriver deliveryDriver;
}
