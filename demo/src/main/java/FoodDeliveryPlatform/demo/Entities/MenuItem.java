package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class MenuItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;
    private Boolean isVegetarian;
    private Double calories;

    @ManyToOne
    @JoinColumn(name = "restaurantID")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem")
    private List<OrderItem> orderItems;
}
