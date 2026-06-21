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
@Table(name = "restaurant")
public class Restaurant extends BaseEntity{
    private String name;
    private String description;
    private String cuisineType;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Double minOrderAmount;
    private Double deliveryFee;
    private Boolean acceptingOrders;

    @ManyToOne
    @JoinColumn(name = "restaurantOwnerID")
    private RestaurantOwner restaurantOwner;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<ComboMeal> comboMeals;
}
