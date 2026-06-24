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
public class CustomerResponseDTO extends PersonDTO {
    /*private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passwordHash;*/

    public static CustomerResponseDTO convertToDTO(Customer entity) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
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
    public static List<CustomerResponseDTO> convertToDTO(List<Customer> entities) {
        List<CustomerResponseDTO> dtos = new ArrayList<>();
        for (Customer entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
