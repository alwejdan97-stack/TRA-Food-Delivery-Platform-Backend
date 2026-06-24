package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends BaseEntity{
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private boolean isVegetarian;
    private double calories;

    @ManyToOne
    @JoinColumn()
    private Restaurant restaurant;

    @OneToMany()
    private List<OrderItem> orderItems;
}
