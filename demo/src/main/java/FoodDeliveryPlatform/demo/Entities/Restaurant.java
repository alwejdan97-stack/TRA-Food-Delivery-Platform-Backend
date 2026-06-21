package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;

    private String name;
    private String description;
    private String cuisineType;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private double minOrderAmount;
    private double deliveryFee;
    private Boolean acceptingOrders;

    @OneToMany(mappedBy = "Restaurant")
    private List<RestaurantOwner> restaurantOwners;

    @OneToMany(mappedBy = "Restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "Restaurant")
    private List<ComboMeal> comboMeals;
}
