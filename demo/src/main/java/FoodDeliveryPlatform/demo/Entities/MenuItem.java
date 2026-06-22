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
    private Double price;
    private Boolean isAvailable;
    private Boolean isVegetarian;
    private Double calories;

    @ManyToOne
    @JoinColumn()
    private Restaurant restaurant;

    @OneToMany()
    private List<OrderItem> orderItems;
}
