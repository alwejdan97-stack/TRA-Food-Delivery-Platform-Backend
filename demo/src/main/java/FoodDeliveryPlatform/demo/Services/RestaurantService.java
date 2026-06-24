package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {


    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto, Integer ownerId){}

    public RestaurantResponseDTO toggleAcceptingOrders(Integer restaurantId, boolean status){}

    public RestaurantResponseDTO updateDeliveryFee(Integer restaurantId, double newFee){}

    public RestaurantResponseDTO getRestaurantsByCuisine(String cuisine){}

    public RestaurantResponseDTO getRestaurantsUnderDeliveryFee(double maxFee){}

    public RestaurantResponseDTO getMenuForRestaurant(Integer restaurantId){}

    public RestaurantResponseDTO bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease){}
}
