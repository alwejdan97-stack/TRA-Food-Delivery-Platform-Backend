package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryDriverDTORequest extends PersonRequest{
    public void applyToEntity(DeliveryDriver entity){
        CustomerDTORequest  dto=new CustomerDTORequest();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        //dto.setPasswordHash(entity.getPasswordHash());
    }
}
