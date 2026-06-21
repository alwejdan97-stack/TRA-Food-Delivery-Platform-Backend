package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String targetType;
    private String rating;
    private String comment;
    private LocalDateTime createdAt;

    @ManyToOne
    @Column(name = "customerID")
    private List<Customer> customers;

    @ManyToOne
    @Column(name = "restaurantID" ,nullable = false)
    private List<Restaurant>

    @ManyToOne
    @Column(name = "deliverDriverID",nullable = false)
    private List<DeliveryDriver> deliveryDrivers;
}
