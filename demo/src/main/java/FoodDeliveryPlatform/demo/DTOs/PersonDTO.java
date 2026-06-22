package FoodDeliveryPlatform.demo.DTOs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class PersonDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "First Name Can't Be Empty")
    private String firstName;
    @NotBlank(message = "Last Name Can't Be Empty")
    private String lastName;
    @NotBlank(message = "Email Can't Be Empty")
    @Email
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{8,15}$")
    private String phone;
    private String password;
}
