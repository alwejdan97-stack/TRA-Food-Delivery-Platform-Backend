package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.OrderItem;
import FoodDeliveryPlatform.demo.Entities.Orders;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemDTOResponse {
    @NotNull
    private Integer id;
    @PositiveOrZero
    private Integer quantity;
    @DecimalMin("0.0")
    private Double unitPrice;
    @DecimalMin("0.0")
    private Double totalPrice;
    @NotBlank(message = "Instructions Can't Be Empty")
    private String specialInstructions;

    public static OrderItemDTOResponse convertToDTO(OrderItem entity) {
        OrderItemDTOResponse dto = new OrderItemDTOResponse();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setSpecialInstructions(entity.getSpecialInstructions());
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<OrderItemDTOResponse> convertToDTO(List<OrderItem> entities) {
        List<OrderItemDTOResponse> dtos = new ArrayList<>();
        for (OrderItem entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
