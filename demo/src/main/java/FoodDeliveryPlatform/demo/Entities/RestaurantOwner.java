package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwner extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;
    private String businessLicenseCode;

    @OneToMany()
    private List<Restaurant> restaurants;
}
