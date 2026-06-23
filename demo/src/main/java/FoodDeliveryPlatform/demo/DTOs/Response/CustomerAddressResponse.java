package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerAddressResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Street Can't Be Null")
    private String street;
    @NotBlank(message = "City Can't Be Null")
    private String city;
    @NotBlank(message = "Building Can't Be Null")
    private String building;

    public static CustomerAddressResponse convertToDTO(CustomerAddress entity) {
        CustomerAddressResponse dto = new CustomerAddressResponse();
        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setBuilding(entity.getBuilding());
        return dto;
    }

    public static List<CustomerAddressResponse> convertToDTO(List<CustomerAddress> entities) {
        List<CustomerAddressResponse> dtos = new ArrayList<>();
        for (CustomerAddress entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
