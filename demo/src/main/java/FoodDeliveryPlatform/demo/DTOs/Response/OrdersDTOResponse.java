package FoodDeliveryPlatform.demo.DTOs.Response;

import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.Orders;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrdersDTOResponse {
    private Integer id;
    private String orderCode;
    private LocalDate orderDate;
    private Boolean status;
    private Double subtotal;
    private Double deliveryFee;
    private Double discountAmount;
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
