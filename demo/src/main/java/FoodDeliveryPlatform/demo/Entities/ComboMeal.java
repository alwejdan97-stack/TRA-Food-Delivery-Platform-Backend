package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ComboMeal")
public class ComboMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String comboName;
    private String description;
    private double totalPrice;
    private Boolean isAvailable;

    @ManyToOne
    @Column(name = "restaurantID")
    private List<Restaurant> restaurants;

    @ManyToMany
    private List<MenuItem> menuItems;
}
