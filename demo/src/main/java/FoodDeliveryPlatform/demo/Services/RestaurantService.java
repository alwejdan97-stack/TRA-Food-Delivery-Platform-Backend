package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.RestaurantRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import FoodDeliveryPlatform.demo.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    RestaurantResponseDTO restaurantResponseDTO;
    RestaurantRequestDTO restaurantRequestDTO;
    RestaurantRepository restaurantRepository;
    Restaurant restaurant;
    public static List<Restaurant> restaurantList;

    @Autowired
    public RestaurantService(RestaurantResponseDTO restaurantResponseDTO, RestaurantRequestDTO restaurantRequestDTO, RestaurantRepository restaurantRepository, Restaurant restaurant) {
        this.restaurantResponseDTO = restaurantResponseDTO;
        this.restaurantRequestDTO = restaurantRequestDTO;
        this.restaurantRepository = restaurantRepository;
        this.restaurant = restaurant;
    }

    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto, Integer ownerId){
        Restaurant r
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setAcceptingOrders(true);
        restaurant.setMinOrderAmount(dto.getMinOrderAmount());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
        restaurant.setIsActive(true);

        Restaurant newRestaurant=restaurantRepository.save(restaurant);

        /*restaurantResponseDTO.setId(newRestaurant.getId());
        restaurantResponseDTO.setName(newRestaurant.getName());
        restaurantResponseDTO.setDescription(newRestaurant.getDescription());
        restaurantResponseDTO.setAcceptingOrders(newRestaurant.getAcceptingOrders());
        restaurantResponseDTO.setOpeningTime(newRestaurant.getOpeningTime());
        restaurantResponseDTO.setClosingTime(newRestaurant.getClosingTime());
        restaurantResponseDTO.setMinOrderAmount(newRestaurant.getMinOrderAmount());*/

        return RestaurantResponseDTO.convertToDTO(newRestaurant);
    }

    public RestaurantResponseDTO toggleAcceptingOrders(Integer restaurantId, boolean status){}

    public RestaurantResponseDTO updateDeliveryFee(Integer restaurantId, double newFee){}

    public RestaurantResponseDTO getRestaurantsByCuisine(String cuisine){}

    public RestaurantResponseDTO getRestaurantsUnderDeliveryFee(double maxFee){}

    public RestaurantResponseDTO getMenuForRestaurant(Integer restaurantId){}

    public RestaurantResponseDTO bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease){}
}
