package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantDTOResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Name Can't Be Empty")
    private String name;
    @NotBlank(message = "Description Can't Be Empty")
    private String description;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @Min(1)
    private Double minOrderAmount;
    @Pattern(regexp = "YES|...| NO")
    private Boolean acceptingOrders;

    public static RestaurantDTOResponse convertToDTO(Restaurant entity) {
        RestaurantDTOResponse dto = new RestaurantDTOResponse();
        dto.setId(entity.getId());
        dto.setName(dto.getName());
        dto.setDescription(entity.getDescription());
        dto.setOpeningTime(entity.getOpeningTime());
        dto.setClosingTime(entity.getClosingTime());
        dto.setMinOrderAmount(entity.getMinOrderAmount());
        dto.setAcceptingOrders(entity.getAcceptingOrders());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<RestaurantDTOResponse> convertToDTO(List<Restaurant> entities) {
        List<RestaurantDTOResponse> dtos = new ArrayList<>();
        for (Restaurant entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
