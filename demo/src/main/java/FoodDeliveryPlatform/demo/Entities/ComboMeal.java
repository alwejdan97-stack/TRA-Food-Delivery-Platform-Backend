package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class ComboMeal extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String comboName;
    private String description;
    private Double totalPrice;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(name="comboMeal_menuItem",joinColumns=@JoinColumn(name="comboMealID"),inverseJoinColumns = @JoinColumn(name="menuItemID"))
    private List<MenuItem> menuItems;
}
