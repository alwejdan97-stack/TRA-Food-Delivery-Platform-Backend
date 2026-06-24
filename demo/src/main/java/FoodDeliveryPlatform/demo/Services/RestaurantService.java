package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.RestaurantRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Entities.MenuItem;
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
    MenuItemResponseDTO menuItemResponseDTO;
    Restaurant restaurant;
    public static List<Restaurant> restaurantList;

    @Autowired
    public RestaurantService(RestaurantResponseDTO restaurantResponseDTO, RestaurantRequestDTO restaurantRequestDTO, RestaurantRepository restaurantRepository, RestaurantOwner restaurantOwner, MenuItemResponseDTO menuItemResponseDTO, Restaurant restaurant) {
        this.restaurantResponseDTO = restaurantResponseDTO;
        this.restaurantRequestDTO = restaurantRequestDTO;
        this.restaurantRepository = restaurantRepository;
        this.restaurantOwner=restaurantOwner;
        this.menuItemResponseDTO=menuItemResponseDTO;
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
        Restaurant foundRestaurant=restaurantRepository.findById(restaurantId).get();
        if(foundRestaurant==null || restaurantList.isEmpty() || !restaurantRepository.existsById(restaurantId)){
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

    public List<RestaurantResponseDTO> getRestaurantsUnderDeliveryFee(double maxFee){
        List<Restaurant> foundRestaurant=restaurantRepository.findByDeliveryFeeLessThanEqual(maxFee);
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

    public List<MenuItemResponseDTO> getMenuForRestaurant(Integer restaurantId) {
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).get();
        if (foundRestaurant == null || restaurantList.isEmpty() || !restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant1 = restaurantList.get(restaurantId);
        List<MenuItemResponseDTO> menuResponse = new ArrayList<>();

        for (MenuItem mi : restaurant1.getMenuItems()) {
            menuItemResponseDTO.setId(mi.getId());
            menuItemResponseDTO.setName(mi.getName());
            menuItemResponseDTO.setAvailable(mi.getIsAvailable());
            menuItemResponseDTO.setPrice(mi.getPrice());

            menuResponse.add(menuItemResponseDTO);
        }
        return menuResponse;
    }

    public List<MenuItemResponseDTO> bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease){
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId).get();
        if (foundRestaurant == null || restaurantList.isEmpty() || !restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant1 = restaurantList.get(restaurantId);
        List<MenuItemResponseDTO> updatedMenu = new ArrayList<>();

        for (MenuItem mi : restaurant1.getMenuItems()){
            double currentPrice=mi.getPrice();
            double newPrice=currentPrice+(currentPrice*(percentageIncrease / 100.0));
            mi.setPrice(newPrice);

            menuItemResponseDTO.setId(mi.getId());
            menuItemResponseDTO.setName(mi.getName());
            menuItemResponseDTO.setPrice(newPrice);

            updatedMenu.add(menuItemResponseDTO);
        }
        return updatedMenu;
    }
}
