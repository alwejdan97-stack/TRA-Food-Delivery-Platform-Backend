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
public class RestaurantOwner extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;
    private String businessLicenseCode;

    @OneToMany(mappedBy = "restaurantOwner")
    private List<Restaurant> restaurants;
}
