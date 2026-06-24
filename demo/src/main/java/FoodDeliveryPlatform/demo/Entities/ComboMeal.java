package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ComboMeal extends BaseEntity{
    private String comboName;
    private String description;
    private double totalPrice;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    private List<MenuItem> menuItems;
}
