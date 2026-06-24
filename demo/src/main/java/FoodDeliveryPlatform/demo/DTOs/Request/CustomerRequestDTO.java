package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequestDTO extends PersonDTO {
    public void applyToEntity(Customer entity){
        entity.setId(this.getId());
        entity.setFirstName(this.getFirstName());
        entity.setLastName(this.getLastName());
        entity.setEmail(this.getEmail());
        entity.setPhone(this.getPhone());
        //dto.setPasswordHash(entity.getPasswordHash());
        //return dto;
    }
}
