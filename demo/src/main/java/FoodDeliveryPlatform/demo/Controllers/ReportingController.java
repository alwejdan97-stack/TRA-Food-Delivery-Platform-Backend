package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    @GetMapping("/getRestaurantRevenueByDay/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getRestaurantRevenueByDay(@PathVariable Integer restaurantId, @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        response.put("restaurantId", restaurantId);
        response.put("date", date);
        response.put("dailyRevenue", 0.0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLifetimeOrdersCount/orders/count/restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getLifetimeOrdersCount(@PathVariable Integer restaurantId) {
        Map<String, Object> response = new HashMap<>();
        response.put("restaurantId", restaurantId);
        response.put("lifetimeOrders", 0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getTopLoyaltyCustomers/customers/top-loyalty")
    public ResponseEntity<List<CustomerResponseDTO>> getTopLoyaltyCustomers() {
        List<CustomerResponseDTO> topCustomers = new ArrayList<>();
        return ResponseEntity.ok(topCustomers);
    }

    @GetMapping("/getDriversLeaderboard/drivers/leaderboard")
    public ResponseEntity<List<DeliveryDriverResponseDTO>> getDriversLeaderboard() {
        List<DeliveryDriverResponseDTO> topDrivers = new ArrayList<>();
        return ResponseEntity.ok(topDrivers);
    }

    @GetMapping("/getPlatformDailySummary/platform/daily-summary")
    public ResponseEntity<Map<String, Object>> getPlatformDailySummary(@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        response.put("date", date);
        response.put("totalOrdersCount", 0);
        response.put("totalDeliveryFeesCollected", 0.0);
        response.put("totalPlatformRevenue", 0.0);
        return ResponseEntity.ok(response);
    }
}
