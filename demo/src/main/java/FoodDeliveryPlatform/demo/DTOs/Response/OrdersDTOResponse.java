package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.Orders;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrdersDTOResponse {
    @NotNull
    private Integer id;
    @NotBlank(message = "Code Can't Be Null")
    private String orderCode;
    private LocalDate orderDate;
    @Pattern(regexp = "PENDING|...| CANCELLED")
    private Boolean status;
    @DecimalMin("0.0")
    private Double subtotal;
    @DecimalMin("0.0")
    private Double deliveryFee;
    @DecimalMin("0.0")
    private Double discountAmount;
    @DecimalMin("0.0")
    private Double totalAmount;

    public static OrdersDTOResponse convertToDTO(Orders entity) {
        OrdersDTOResponse dto = new OrdersDTOResponse();
        dto.setId(entity.getId());
        dto.setOrderCode(dto.getOrderCode());
        dto.setOrderDate(entity.getOrderDate());
        dto.setStatus(entity.getStatus());
        dto.setSubtotal(entity.getSubtotal());
        dto.setDeliveryFee(entity.getDeliveryFee());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setTotalAmount(entity.getTotalAmount());
        return dto;
    }

    public static List<OrdersDTOResponse> convertToDTO(List<Orders> entities) {
        List<OrdersDTOResponse> dtos = new ArrayList<>();
        for (Orders entity : entities) {
            dtos.add(convertToDTO(entity));
        }
        return dtos;
    }
}
