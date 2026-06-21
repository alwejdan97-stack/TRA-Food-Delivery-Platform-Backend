package FoodDeliveryPlatform.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.function.array.AbstractArrayTrimFunction;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;
    private int loyaltyPoints;
    private String customerCode;
}
