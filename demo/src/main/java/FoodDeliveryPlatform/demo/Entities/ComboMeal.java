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
    private Double totalPrice;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany
    private List<MenuItem> menuItems;
}
