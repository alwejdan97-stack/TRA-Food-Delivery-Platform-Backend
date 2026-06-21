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
@Table(name = "customerReviews")
public class Review extends BaseEntity{
    private String targetType;
    private String rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "restaurantID" ,nullable = true)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn (name = "deliverDriverID",nullable = true)
    private DeliveryDriver deliveryDriver;
}
