package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTORequest extends PersonDTO {
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
