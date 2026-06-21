package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.function.array.AbstractArrayTrimFunction;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = true)
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
