package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.RestaurantRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Entities.MenuItem;
import FoodDeliveryPlatform.demo.Entities.Restaurant;
import FoodDeliveryPlatform.demo.Entities.RestaurantOwner;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.MenuItemRepository;
import FoodDeliveryPlatform.demo.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository;
    MenuItemRepository menuItemResponseDTO;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemResponseDTO) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemResponseDTO = menuItemResponseDTO;
    }

    public List<RestaurantResponseDTO> createRestaurant(RestaurantRequestDTO dto, Integer ownerId){
        List<Restaurant> restaurants=restaurantRepository.findByRestaurantOwnerId(ownerId);

        if(restaurants.isEmpty()){
            throw new ResourceNotFoundException(ErrorMessage.OWNER_NOT_FOUND);
        }
        RestaurantOwner owner=restaurants.get(0).getRestaurantOwner();

        Restaurant restaurant=new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setAcceptingOrders(true);
        restaurant.setMinOrderAmount(dto.getMinOrderAmount());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
        restaurant.setIsActive(true);
        restaurant.setRestaurantOwner(owner);

        Restaurant newRestaurant=restaurantRepository.save(restaurant);
        List<RestaurantResponseDTO> restaurantResponseDTOList=new ArrayList<>();
        restaurantResponseDTOList.add(RestaurantResponseDTO.convertToDTO(newRestaurant));
        /*restaurantResponseDTO.setId(newRestaurant.getId());
        restaurantResponseDTO.setName(newRestaurant.getName());
        restaurantResponseDTO.setDescription(newRestaurant.getDescription());
        restaurantResponseDTO.setAcceptingOrders(newRestaurant.getAcceptingOrders());
        restaurantResponseDTO.setOpeningTime(newRestaurant.getOpeningTime());
        restaurantResponseDTO.setClosingTime(newRestaurant.getClosingTime());
        restaurantResponseDTO.setMinOrderAmount(newRestaurant.getMinOrderAmount());*/

        return restaurantResponseDTOList;
    }

    public List<RestaurantResponseDTO> toggleAcceptingOrders(Integer restaurantId, boolean status){
        Optional<Restaurant> restaurants=restaurantRepository.findById(restaurantId);
        if(restaurants.isEmpty() || !restaurantRepository.existsById(restaurantId)){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant1=restaurants.get();

        restaurant1.setAcceptingOrders(status);
        restaurant1.setUpdatedDate(LocalDate.now());

        Restaurant updatedRestaurant=restaurantRepository.save(restaurant1);
        List<RestaurantResponseDTO> restaurantResponseDTOList=new ArrayList<>();
        restaurantResponseDTOList.add(RestaurantResponseDTO.convertToDTO(updatedRestaurant));

        return restaurantResponseDTOList;
    }

    public RestaurantResponseDTO updateDeliveryFee(Integer restaurantId, double newFee){
        Restaurant foundRestaurant=restaurantRepository.findById(restaurantId).get();
        if(foundRestaurant==null || !restaurantRepository.existsById(restaurantId)){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant=new Restaurant();

        restaurant.setDeliveryFee(newFee);
        restaurant.setUpdatedDate(LocalDate.now());

        Restaurant updatedRestaurant=restaurantRepository.save(restaurant);

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
        Optional<Restaurant> restaurants = restaurantRepository.findById(restaurantId);
        if (restaurants == null || restaurants.isEmpty() || !restaurantRepository.existsById(restaurantId) || !restaurants.get().getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant = restaurants.get();
        List<MenuItemResponseDTO> menuResponse = new ArrayList<>();

        for (MenuItem mi : restaurant.getMenuItems()) {
            MenuItemResponseDTO menuItemResponseDTO=new MenuItemResponseDTO();
            menuItemResponseDTO.setId(mi.getId());
            menuItemResponseDTO.setName(mi.getName());
            menuItemResponseDTO.setAvailable(mi.getIsAvailable());
            menuItemResponseDTO.setPrice(mi.getPrice());

            menuResponse.add(menuItemResponseDTO);
        }
        return menuResponse;
    }

    public List<MenuItemResponseDTO> bulkUpdateMenuItemPrices(Integer restaurantId, double percentageIncrease){
        Optional<Restaurant> restaurants = restaurantRepository.findById(restaurantId);
        if (restaurants.isEmpty() || !restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Restaurant restaurant1 = restaurants.get();
        List<MenuItemResponseDTO> updatedMenu = new ArrayList<>();

        for (MenuItem mi : restaurant1.getMenuItems()){
            double currentPrice=mi.getPrice();
            double newPrice=currentPrice+(currentPrice*(percentageIncrease / 100.0));
            mi.setPrice(newPrice);

            MenuItemResponseDTO menuItemResponseDTO=new MenuItemResponseDTO();
            menuItemResponseDTO.setId(mi.getId());
            menuItemResponseDTO.setName(mi.getName());
            menuItemResponseDTO.setPrice(newPrice);

            updatedMenu.add(menuItemResponseDTO);
        }
        return updatedMenu;
    }
}
