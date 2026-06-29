package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.DTOs.Summary.OrderItemDTOSummary;
import FoodDeliveryPlatform.demo.DTOs.Summary.OrdersDTOSummary;
import FoodDeliveryPlatform.demo.Entities.Orders;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrdersResponseDTO {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Code Can't Be Null")
    private String orderCode;
    private LocalDate orderDate;
    @Pattern(regexp = "PENDING|...| CANCELLED |...| DELIVERED")
    private String status;
    @DecimalMin("0.0")
    private double subtotal;
    @DecimalMin("0.0")
    private double deliveryFee;
    @DecimalMin("0.0")
    private double discountAmount;
    @DecimalMin("0.0")
    private double totalAmount;
    @DecimalMin("0.0")
    private Integer estimatedMinutesRemaining;
    private OrdersDTOSummary ordersDTOSummary;

    public static OrdersResponseDTO convertToDTO(Orders entity) {
        OrdersResponseDTO dto = new OrdersResponseDTO();
        dto.setId(entity.getId());
        dto.setOrderCode(dto.getOrderCode());
        dto.setOrderDate(entity.getOrderDate());
        dto.setStatus(entity.getStatus());
        dto.setSubtotal(entity.getSubtotal());
        dto.setDeliveryFee(entity.getDeliveryFee());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setTotalAmount(entity.getTotalAmount());

        OrdersDTOSummary summary=new OrdersDTOSummary();
        summary.setId(entity.getId());
        summary.setStatus(entity.getStatus());
        summary.setDeliveryNotes(entity.getDeliveryNotes());
        summary.setOrderCode(entity.getOrderCode());

        dto.setOrdersDTOSummary(summary);
        return dto;
    }

    @NotEmpty
    @Valid
    public static List<OrdersResponseDTO> convertToDTO(List<Orders> entities) {
        List<OrdersResponseDTO> dtos = new ArrayList<>();
        for (Orders entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
