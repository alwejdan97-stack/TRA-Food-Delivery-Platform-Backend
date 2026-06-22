package FoodDeliveryPlatform.demo.DTOs.Summary;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTOSummary extends PersonDTO {
    /*private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;*/
    //private String passwordHash;

    public static CustomerDTOSummary fromEntity(Customer entity) {
        CustomerDTOSummary dto = new CustomerDTOSummary();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());

        return dto;
    }
}
