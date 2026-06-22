package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.dialect.function.array.AbstractArrayTrimFunction;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;
    private String passwordHash;
    private Integer loyaltyPoints;
    private String customerCode;

    @OneToMany(mappedBy = "customer")
    private List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;
}
