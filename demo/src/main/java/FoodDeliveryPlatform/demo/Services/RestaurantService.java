package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.RestaurantRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwnerRepository;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    RestaurantResponseDTO restaurantResponseDTO;
    RestaurantRequestDTO restaurantRequestDTO;
    RestaurantRepository restaurantRepository;
    RestaurantOwner restaurantOwner;
    Restaurant restaurant;
    public static List<Restaurant> restaurantList;

    @Autowired
    public RestaurantService(RestaurantResponseDTO restaurantResponseDTO, RestaurantRequestDTO restaurantRequestDTO, RestaurantRepository restaurantRepository, RestaurantOwner restaurantOwner, Restaurant restaurant) {
        this.restaurantResponseDTO = restaurantResponseDTO;
        this.restaurantRequestDTO = restaurantRequestDTO;
        this.restaurantRepository = restaurantRepository;
        this.restaurantOwner=restaurantOwner;
        this.restaurant = restaurant;
    }

    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO dto, Integer ownerId){
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setAcceptingOrders(true);
        restaurant.setMinOrderAmount(dto.getMinOrderAmount());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
        restaurant.setIsActive(true);
        //restaurant.setRestaurantOwner(ownerId);

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

    public RestaurantResponseDTO toggleAcceptingOrders(Integer restaurantId, boolean status){
        if(restaurantList.isEmpty() || !restaurantRepository.existsById(restaurantId)){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant1=restaurantList.get(restaurantId);

        restaurant1.setAcceptingOrders(status);
        restaurant1.setUpdatedDate(LocalDate.now());

        Restaurant updatedRestaurant=restaurantRepository.save(restaurant1);

        return RestaurantResponseDTO.convertToDTO(updatedRestaurant);
    }

    public RestaurantResponseDTO updateDeliveryFee(Integer restaurantId, double newFee){
        if(restaurantList.isEmpty() || !restaurantRepository.existsById(restaurantId)){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant1=restaurantList.get(restaurantId);

        restaurant1.setDeliveryFee(newFee);
        restaurant1.setUpdatedDate(LocalDate.now());

        Restaurant updatedRestaurant=restaurantRepository.save(restaurant1);

        return RestaurantResponseDTO.convertToDTO(updatedRestaurant);
    }

    public List<RestaurantResponseDTO> getRestaurantsByCuisine(String cuisine){
        List<Restaurant> foundRestaurant=restaurantRepository.findByCuisineTypeIgnoreCase(cuisine);
        if(foundRestaurant.isEmpty()){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        List<RestaurantResponseDTO> responseDTOss=new ArrayList<>();
        for(Restaurant r:foundRestaurant){
            RestaurantResponseDTO dto=new RestaurantResponseDTO();
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setCuisineType(r.getCuisineType());
            dto.setAcceptingOrders(r.getAcceptingOrders());
            dto.setDeliveryFee(r.getDeliveryFee());

            responseDTOss.add(dto);
        }
        return responseDTOss;
    }

    public List<RestaurantResponseDTO> getRestaurantsUnderDeliveryFee(double maxFee){}

    public List<RestaurantResponseDTO> getMenuForRestaurant(Integer restaurantId){}

    public RestaurantResponseDTO bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease){}
}
