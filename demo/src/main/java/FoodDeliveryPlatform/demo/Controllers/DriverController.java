package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Request.DeliveryDriverRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.Services.DeliveryDriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("(/api/drivers")
public class DriverController {
    DeliveryDriverService deliveryDriverService;

    @Autowired
    public DriverController(DeliveryDriverService deliveryDriverService) {
        this.deliveryDriverService = deliveryDriverService;
    }

    @PostMapping("/registerDriver")
    public ResponseEntity<DeliveryDriverResponseDTO> registerDriver(@Valid @RequestBody DeliveryDriverRequestDTO request) {
        DeliveryDriverResponseDTO response = deliveryDriverService.registerDriver(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getAllDrivers")
    public ResponseEntity<List<DeliveryDriverResponseDTO>> getAllDrivers() {
        List<DeliveryDriverResponseDTO> response = deliveryDriverService.getAllDrivers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/online")
    public ResponseEntity<List<DeliveryDriverResponseDTO>> getOnlineDrivers() {
        List<DeliveryDriverResponseDTO> response = deliveryDriverService.getOnlineDrivers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/toggleOnlineStatus/{id}/status")
    public ResponseEntity<DeliveryDriverResponseDTO> toggleOnlineStatus(
            @PathVariable Integer id,
            @RequestParam(name = "isOnline") boolean isOnline) {
        DeliveryDriverResponseDTO response = deliveryDriverService.toggleOnlineStatus(id, isOnline);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateLocation/{id}/location")
    public ResponseEntity<DeliveryDriverResponseDTO> updateLocation(@PathVariable Integer id, @RequestParam(name = "lat") Double lat, @RequestParam(name = "lng") Double lng) {
        DeliveryDriverResponseDTO response = deliveryDriverService.updateLocation(id, lat, lng);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
