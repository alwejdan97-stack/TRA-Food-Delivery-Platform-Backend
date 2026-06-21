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
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = true)
    private String email;

    private String phone;
    private String passwordHash;
    private Integer loyaltyPoints;
    private String customerCode;

    @OneToMany(mappedBy = "Customer")
    private List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "Customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "Customer")
    private List<Review> reviews;
}
