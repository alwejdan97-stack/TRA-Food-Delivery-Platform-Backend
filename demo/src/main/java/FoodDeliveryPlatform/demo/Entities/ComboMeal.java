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
@Table
public class ComboMeal extends BaseEntity{
    private String comboName;
    private String description;
    private Double totalPrice;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(name="comboMeal_menuItem",joinColumns=@JoinColumn(name="comboMealID"),inverseJoinColumns = @JoinColumn(name="menuItemID"))
    private List<MenuItem> menuItems;
}
