package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;
    private String passwordHash;
    private Integer loyaltyPoints;
    private String customerCode;

    @OneToMany()
    private List<CustomerAddress> customerAddresses;

    @OneToMany()
    private List<Orders> orders;

    @OneToMany()
    private List<Review> reviews;
}
