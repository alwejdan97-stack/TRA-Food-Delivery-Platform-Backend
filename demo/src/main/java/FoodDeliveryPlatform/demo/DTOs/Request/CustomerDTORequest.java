package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Customer;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTORequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;

    public void applyToEntity(Customer entity){
        CustomerDTORequest  dto=new CustomerDTORequest();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        //dto.setPasswordHash(entity.getPasswordHash());
        //return dto;
    }
}
