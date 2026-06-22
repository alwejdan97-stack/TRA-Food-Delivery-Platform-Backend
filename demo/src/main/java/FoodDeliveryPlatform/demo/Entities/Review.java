package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity{
    private String targetType;
    private String rating;
    private String comment;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private DeliveryDriver deliveryDriver;
}
