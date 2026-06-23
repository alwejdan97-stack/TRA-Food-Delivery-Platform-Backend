package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.PersonDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDTOResponse extends PersonDTO {
    /*private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;*/

    public static CustomerDTOResponse convertToDTO(Customer entity) {
        CustomerDTOResponse dto = new CustomerDTOResponse();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        //dto.setPasswordHash(entity.getPasswordHash());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<CustomerDTOResponse> convertToDTO(List<Customer> entities) {
        List<CustomerDTOResponse> dtos = new ArrayList<>();
        for (Customer entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
