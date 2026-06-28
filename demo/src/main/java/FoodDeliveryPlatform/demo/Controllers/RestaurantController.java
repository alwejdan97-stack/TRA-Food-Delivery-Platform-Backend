package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Request.ComboMealRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.MenuItemRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.RestaurantRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.ComboMealResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/owner/{ownerId} ")
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@PathVariable Integer ownerId, @Valid @RequestBody RestaurantRequestDTO dto) {

        List<RestaurantResponseDTO> responseList = restaurantService.createRestaurant(dto, ownerId);
        return new ResponseEntity<>(responseList.get(0), HttpStatus.CREATED);
    }

    @PostMapping("/createCombo/{id}/combos ")
    public ResponseEntity<ComboMealResponseDTO> createCombo(@PathVariable Integer id, @Valid@RequestBody ComboMealRequestDTO comboRequestDto) {
        ComboMealResponseDTO response = restaurantService.createCombo(id, comboRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/addMenuItem/{id}/menu")
    public ResponseEntity<MenuItemResponseDTO> addMenuItem(@PathVariable Integer id, @Valid @RequestBody MenuItemRequestDTO menuItemRequestDto) {
        MenuItemResponseDTO response = restaurantService.addMenuItem(id, menuItemRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/toggleAcceptingOrders/{id}/toggle-orders")
    public ResponseEntity<RestaurantResponseDTO> toggleAcceptingOrders(@PathVariable Integer id, @RequestParam(name = "accepting") boolean accepting) {
        List<RestaurantResponseDTO> responseList = restaurantService.toggleAcceptingOrders(id, accepting);
        return ResponseEntity.ok(responseList.get(0));
    }

    @PutMapping("/{id}/fee/{newFee}")
    public ResponseEntity<RestaurantResponseDTO> updateDeliveryFee(
            @PathVariable Integer id,
            @PathVariable double newFee) {
        RestaurantResponseDTO response = restaurantService.updateDeliveryFee(id, newFee);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/toggleItemAvailability/menu/{itemId}/availablity")
    public ResponseEntity<MenuItemResponseDTO> toggleItemAvailability(@PathVariable Integer itemId, @RequestParam(name = "status") boolean status) {
        MenuItemResponseDTO response = restaurantService.toggleItemAvailability(itemId, status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/bulk-price-increase")
    public ResponseEntity<List<MenuItemResponseDTO>> bulkUpdateMenuItemPrices(
            @PathVariable Integer id,
            @RequestParam(name = "percentage", defaultValue = "0.0") double percentage) {
        List<MenuItemResponseDTO> updatedMenu = restaurantService.bulkUpdateMenuItemPrices(id, percentage);
        return ResponseEntity.ok(updatedMenu);
    }

    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable Integer id) {
        RestaurantResponseDTO response = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRestaurantsByCuisine/cuisine/{cuisine}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantsByCuisine(@PathVariable String cuisine) {
        List<RestaurantResponseDTO> responses = restaurantService.getRestaurantsByCuisine(cuisine);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/getMenuForRestaurant/{id}/menu")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuForRestaurant(@PathVariable Integer id) {
        List<MenuItemResponseDTO> menu = restaurantService.getMenuForRestaurant(id);
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/getCombosForRestaurant/{id}/combos")
    public ResponseEntity<List<ComboMealResponseDTO>> getCombosForRestaurant(@PathVariable Integer id) {
        List<ComboMealResponseDTO> combos = restaurantService.getCombosForRestaurant(id);
        return ResponseEntity.ok(combos);
    }

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        List<RestaurantResponseDTO> responses = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(responses);
    }
}
