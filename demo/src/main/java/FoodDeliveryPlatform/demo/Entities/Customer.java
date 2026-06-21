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
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    private String phone;
    private String passwordHash;
    private int loyaltyPoints;
    private String customerCode;

    @OneToMany(mappedBy = "Customer")
    private List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "Customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "Customer")
    private List<Review> reviews;
}
