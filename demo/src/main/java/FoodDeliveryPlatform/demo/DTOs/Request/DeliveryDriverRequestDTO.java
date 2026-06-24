package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryDriverRequestDTO extends PersonDTO {
    public void applyToEntity(DeliveryDriver entity){
        //DeliveryDriverDTORequest  dto=new DeliveryDriverDTORequest();
        entity.setId(this.getId());
        entity.setFirstName(this.getFirstName());
        entity.setLastName(this.getLastName());
        entity.setEmail(this.getEmail());
        entity.setPhone(this.getPhone());
        //dto.setPasswordHash(entity.getPasswordHash());
    }
}
