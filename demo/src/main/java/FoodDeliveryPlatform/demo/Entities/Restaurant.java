package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant extends BaseEntity{
    private String name;
    private String description;
    private String cuisineType;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private double minOrderAmount;
    private double deliveryFee;
    private Boolean acceptingOrders;

    @ManyToOne
    private RestaurantOwner restaurantOwner;

    @OneToMany()
    private List<MenuItem> menuItems;

    @OneToMany()
    private List<ComboMeal> comboMeals;
}
