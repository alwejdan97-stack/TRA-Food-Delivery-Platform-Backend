package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerAddressResponse {
    private Integer id;
    private String street;
    private String city;
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
