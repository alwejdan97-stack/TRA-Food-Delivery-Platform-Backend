package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.DeliveryDriverDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.OrderItemDTOSummary;
import FoodDeliveryPlatform.demo.Entities.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemResponseDTO {
    @NotNull
    private Integer id;
    @PositiveOrZero
    private int quantity;
    @DecimalMin("0.0")
    private double unitPrice;
    @DecimalMin("0.0")
    private double totalPrice;
    @NotBlank(message = "Instructions Can't Be Empty")
    private String specialInstructions;
    private OrderItemDTOSummary orderItemDTOSummary;

    public static OrderItemResponseDTO convertToDTO(OrderItem entity) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setSpecialInstructions(entity.getSpecialInstructions());

        OrderItemDTOSummary summary=new OrderItemDTOSummary();
        summary.setTotalPrice(entity.getTotalPrice());
        summary.setUnitPrice(entity.getUnitPrice());
        summary.setSpecialInstructions(entity.getSpecialInstructions());
        summary.setQuantity(entity.getQuantity());

        dto.setOrderItemDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<OrderItemResponseDTO> convertToDTO(List<OrderItem> entities) {
        List<OrderItemResponseDTO> dtos = new ArrayList<>();
        for (OrderItem entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
