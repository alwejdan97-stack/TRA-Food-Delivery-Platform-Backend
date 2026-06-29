package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;

import FoodDeliveryPlatform.demo.DTOs.Response.OrdersResponseDTO;
import FoodDeliveryPlatform.demo.Services.OrderService;
import org.springframework.data.domain.Sort;
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

    OrderService orderService;

    @GetMapping("/getRestaurantRevenueByDay/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getRestaurantRevenueByDay(@PathVariable Integer restaurantId, @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        response.put("restaurantId", restaurantId);
        response.put("date", date);
        response.put("dailyRevenue", 0.0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLifetimeOrdersCount/orders/count/restaurant/{restaurantId}")
    public ResponseEntity<List<String>> getLifetimeOrdersCount(@PathVariable Integer restaurantId) {
       List<String> response = new ArrayList<>();
       response.add(restaurantId,"restaurantId");
       response.add(0,"lifetimeOrders");

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
    public ResponseEntity<List<String>> getPlatformDailySummary(@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<String> response = new ArrayList<>();

        response.add("date"+ date);
        response.add("totalOrdersCount"+ 0);
        response.add("totalDeliveryFeesCollected"+ 0.0);
        response.add("totalPlatformRevenue"+ 0.0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/revenue/restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getCustomRestaurantRevenue(
            @PathVariable Integer restaurantId,
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        Map<String, Object> response = new HashMap<>();
        response.put("restaurantId", restaurantId);
        response.put("from", from);
        response.put("to", to);
        response.put("totalRevenue", 0.0); // Replace with your repository calculation logic

        return ResponseEntity.ok(response);
    }

    @GetMapping("/drivers/{driverId}/earnings")
    public ResponseEntity<Map<String, Object>> getDriverEarnings(
            @PathVariable Integer driverId,
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        Map<String, Object> response = new HashMap<>();
        response.put("driverId", driverId);
        response.put("from", from);
        response.put("to", to);
        response.put("totalEarnings", 0.0); // Replace with your repository calculation logic

        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders/cancellation-rate")
    public ResponseEntity<Map<String, Object>> getCancellationRate(
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("cancellationRate", 0.0); // e.g., (cancelledOrders / totalOrders) * 100
        response.put("completedCount", 0);
        response.put("cancelledCount", 0);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/platform/busiest-hours")
    public ResponseEntity<List<Map<String, Object>>> getBusiestHours() {
        List<Map<String, Object>> hourlyVolumeReport = new ArrayList<>();

        Map<String, Object> sampleRow = new HashMap<>();
        sampleRow.put("hour", 0);
        sampleRow.put("orderCount", 0);
        hourlyVolumeReport.add(sampleRow);

        return ResponseEntity.ok(hourlyVolumeReport);
    }
}
