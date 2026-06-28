package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryResponseDTO;
import FoodDeliveryPlatform.demo.Services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/order/{orderId}/assign-manual/{driverId}")
    public ResponseEntity<DeliveryDriverResponseDTO> assignDriverToOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer driverId) {
        List<DeliveryDriverResponseDTO> responseList = deliveryService.assignDriverToOrder(orderId, driverId);
        return new ResponseEntity<>(responseList.get(0), HttpStatus.CREATED);
    }

    @PostMapping("/order/{orderId}/assign-auto")
    public ResponseEntity<DeliveryResponseDTO> autoAssignDriver(@PathVariable Integer orderId) {
        DeliveryResponseDTO response = deliveryService.autoAssignDriver(orderId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> getDeliveryById(@PathVariable Integer id) {
        List<DeliveryResponseDTO> trackingList = deliveryService.getDeliveriesForDriver(id, true);
        return ResponseEntity.ok(trackingList.get(0));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<DeliveryResponseDTO>> getDeliveriesByStatus(@PathVariable String status) {
        Boolean isOnlineFlag = "active".equalsIgnoreCase(status) || "online".equalsIgnoreCase(status);
        List<DeliveryResponseDTO> responses = deliveryService.getDeliveriesForDriver(1, isOnlineFlag);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<DeliveryResponseDTO> markDeliveryDelivered(@PathVariable Integer id) {
        List<DeliveryResponseDTO> responseList = deliveryService.markDeliveryDelivered(id);
        return ResponseEntity.ok(responseList.get(0));
    }

    @PutMapping("/{id}/pickup")
    public ResponseEntity<DeliveryResponseDTO> markDeliveryPickedUp(@PathVariable Integer id) {
        List<DeliveryResponseDTO> responseList = deliveryService.markDeliveryPickedUp(id);
        return ResponseEntity.ok(responseList.get(0));
    }
}
