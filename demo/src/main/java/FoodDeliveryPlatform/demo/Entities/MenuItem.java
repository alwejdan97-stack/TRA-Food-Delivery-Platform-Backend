package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MenuItem")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double price;
    private Boolean isAvailable;
    private Boolean isVegetarian;
    private double calories;

    @ManyToOne
    @Column(name = "restaurantID")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "MenuItem")
    private List<OrderItem> orderItems;
}
