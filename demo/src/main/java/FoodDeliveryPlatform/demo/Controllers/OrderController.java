package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Request.CorporateOrderRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.OrderItemRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CorporateOrderResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.OrdersResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Orders;
import FoodDeliveryPlatform.demo.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder/customer/{customerId}/restaurant/{restaurantId}")
    public ResponseEntity<OrdersResponseDTO> createOrder(@PathVariable Integer customerId, @PathVariable Integer restaurantId, @RequestBody List<OrderItemRequestDTO> items) {
        List<OrdersResponseDTO> responseList = orderService.createOrder(customerId, restaurantId, items);
        return new ResponseEntity<>(responseList.get(0), HttpStatus.CREATED);
    }

    @PostMapping("/addMenuItemToOrder/{id}/items")
    public ResponseEntity<List<MenuItemResponseDTO>> addMenuItemToOrder(@PathVariable Integer id, @Valid @RequestBody OrderItemRequestDTO orderItemRequestDto) {
        List<MenuItemResponseDTO> response = orderService.addMenuItemToOrder(id, orderItemRequestDto.getId(), orderItemRequestDto.getQuantity());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/placeCorporateOrder/corporate")
    public ResponseEntity<CorporateOrderResponseDTO> placeCorporateOrder(@Valid @RequestBody CorporateOrderRequestDTO corporateOrderRequestDto) {
        CorporateOrderResponseDTO response = orderService.placeCorporateOrder(corporateOrderRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/applyDiscount/{id}/discount/{amount}")
    public ResponseEntity<List<MenuItemResponseDTO>> applyDiscount(@PathVariable Integer id, @PathVariable double amount) {
        List<MenuItemResponseDTO> response = orderService.applyDiscount(id, amount);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/confirmOrder/{id}/confirm")
    public ResponseEntity<OrdersResponseDTO> confirmOrder(@PathVariable Integer id) {
        OrdersResponseDTO response = orderService.updateOrderStatus(id, "CONFIRMED");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateOrderStatus/{id}/status/{status}")
    public ResponseEntity<OrdersResponseDTO> updateOrderStatus(@PathVariable Integer id, @PathVariable String status) {
        OrdersResponseDTO response = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancelOrder/{id}/cancel")
    public ResponseEntity<OrdersResponseDTO> cancelOrder(@PathVariable Integer id) {
        OrdersResponseDTO response = orderService.cancelOrder(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrdersResponseDTO> getOrderById(@PathVariable Integer id) {
        OrdersResponseDTO response = orderService.calculateOrderTotals(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getActiveOrdersByStatus/restaurant/{restaurantId}")
    public ResponseEntity<List<OrdersResponseDTO>> getActiveOrdersByStatus(@PathVariable Integer restaurantId, @RequestParam(name = "status", defaultValue = "PENDING") String status) {
        List<OrdersResponseDTO> activeOrders = new java.util.ArrayList<>();
        return ResponseEntity.ok(activeOrders);
    }

    @DeleteMapping("/removeMenuItemFromOrder/{id}/items/{itemId}")
    public ResponseEntity<OrdersResponseDTO> removeMenuItemFromOrder(@PathVariable Integer id, @PathVariable Integer itemId) {
        orderService.removeMenuItemFromOrder(id, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/eta")
    public ResponseEntity<OrdersResponseDTO> getOrderETA(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderETA(id));
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<List<OrdersResponseDTO>> getOrderTimeline(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderTimeline(id));
    }

    @PostMapping("/{id}/reorder")
    public ResponseEntity<OrdersResponseDTO> reorder(@PathVariable Integer id) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(orderService.reorderPastOrder(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<OrdersResponseDTO>> getCustomerOrders(
            @PathVariable Integer customerId,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "from", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.getCustomerOrdersFiltered(customerId, status, from, to, page, size));
    }
}
