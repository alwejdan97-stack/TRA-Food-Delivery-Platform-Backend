package FoodDeliveryPlatform.demo.DTOs.Request;

import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerAddressRequestDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Street Can't Be Null")
    private String street;
    @NotBlank(message = "City Can't Be Null")
    private String city;
    @NotBlank(message = "Building Can't Be Null")
    private String building;

    public void applyToEntity(CustomerAddress entity){
        entity.setId(this.id);
        entity.setStreet(this.street);
        entity.setCity(this.city);
        entity.setBuilding(this.building);
    }
}
