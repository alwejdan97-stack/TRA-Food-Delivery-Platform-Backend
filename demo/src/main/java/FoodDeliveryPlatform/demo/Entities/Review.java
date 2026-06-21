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
public class Review extends BaseEntity{
    private String targetType;
    private String rating;
    private String comment;

    @ManyToOne
    @Column(name = "customerID")
    private List<Customer> customers;

    @ManyToOne
    @JoinColumn (name = "restaurantID" ,nullable = false)
    private List<Restaurant> restaurants;

    @ManyToOne
    @JoinColumn (name = "deliverDriverID",nullable = false)
    private List<DeliveryDriver> deliveryDrivers;
}
