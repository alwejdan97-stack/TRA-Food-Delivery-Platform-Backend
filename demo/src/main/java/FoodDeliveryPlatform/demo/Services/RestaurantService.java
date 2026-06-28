package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.ComboMealRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.MenuItemRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.RestaurantRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.ComboMealResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.RestaurantResponseDTO;
import FoodDeliveryPlatform.demo.Entities.*;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.*;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RestaurantService {
    RestaurantRepository restaurantRepository;
    MenuItemRepository menuItemRepository;
    DeliveryDriverRepository driverRepository;
    DeliveryRepository deliveryRepository;
    ReviewRepository reviewRepository;
    OrderRepository orderRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemResponseDTO, DeliveryDriverRepository driverRepository,DeliveryRepository deliveryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemResponseDTO;
        this.driverRepository=driverRepository;
        this.deliveryRepository=deliveryRepository;
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
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        if (restaurant==null || !restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        List<MenuItemResponseDTO> updatedMenu = new ArrayList<>();

        for (MenuItem mi : restaurant.getMenuItems()){
            double currentPrice=mi.getPrice();
            double newPrice=currentPrice*(1*(percentageIncrease / 100.0));
            mi.setPrice(newPrice);

            MenuItemResponseDTO menuItemResponseDTO=new MenuItemResponseDTO();
            menuItemResponseDTO.setId(mi.getId());
            menuItemResponseDTO.setName(mi.getName());
            menuItemResponseDTO.setPrice(newPrice);

            updatedMenu.add(menuItemResponseDTO);
        }
        return updatedMenu;
    }

    public ComboMealResponseDTO createCombo(Integer restaurantId, ComboMealRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        if (restaurant==null || !restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        ComboMeal combo = new ComboMeal();
        combo.setComboName(dto.getComboName());
        combo.setTotalPrice(dto.getTotalPrice());
        combo.setDescription(dto.getDescription());
        combo.setRestaurant(restaurant);

        restaurant.getComboMeals().add(combo);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        ComboMeal savedCombo = savedRestaurant.getComboMeals().get(savedRestaurant.getComboMeals().size() - 1);

        ComboMealResponseDTO response = new ComboMealResponseDTO();
        response.setId(savedCombo.getId());
        response.setComboName(savedCombo.getComboName());
        response.setTotalPrice(savedCombo.getTotalPrice());
        response.setDescription(savedCombo.getDescription());

        return response;
    }

    public MenuItemResponseDTO addMenuItem(Integer restaurantId, MenuItemRequestDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        if (restaurant==null || !restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setIsAvailable(true);
        item.setRestaurant(restaurant);

        restaurant.getMenuItems().add(item);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        MenuItem savedItem = savedRestaurant.getMenuItems().get(savedRestaurant.getMenuItems().size() - 1);

        MenuItemResponseDTO response = new MenuItemResponseDTO();
        response.setId(savedItem.getId());
        response.setName(savedItem.getName());
        response.setPrice(savedItem.getPrice());
        response.setAvailable(savedItem.getIsAvailable());

        return response;
    }

    public RestaurantResponseDTO getRestaurantById(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        if(restaurant==null || !restaurant.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        return RestaurantResponseDTO.convertToDTO(restaurant);
    }

    public MenuItemResponseDTO toggleItemAvailability(Integer itemId, boolean status) {
        MenuItem item = menuItemRepository.findById(itemId).get();
        if (item==null || !item.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ITEM_NOT_FOUND);
        }
        item.setIsAvailable(status);
        MenuItem updatedItem = menuItemRepository.save(item);

        MenuItemResponseDTO response = new MenuItemResponseDTO();
        response.setId(updatedItem.getId());
        response.setName(updatedItem.getName());
        response.setPrice(updatedItem.getPrice());
        response.setAvailable(updatedItem.getIsAvailable());
        return response;
    }

    public List<ComboMealResponseDTO> getCombosForRestaurant(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        if(restaurant==null || !restaurant.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }

        List<ComboMealResponseDTO> dtoList = new ArrayList<>();
        for (ComboMeal combo : restaurant.getComboMeals()) {
            ComboMealResponseDTO dto = new ComboMealResponseDTO();
            dto.setId(combo.getId());
            dto.setComboName(combo.getComboName());
            dto.setTotalPrice(combo.getTotalPrice());
            dto.setDescription(combo.getDescription());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<RestaurantResponseDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantResponseDTO> dtoList = new ArrayList<>();

        for (Restaurant r : restaurants) {
            dtoList.add(RestaurantResponseDTO.convertToDTO(r));
        }
        return dtoList;
    }

    /*public List<Restaurant> getNearbyRestaurants(double lat, double lng, double radiusKm) {
        List<Restaurant> restaurants = restaurantRepository.findByIsActiveTrue();
        List<Restaurant> nearbyRestaurants = new ArrayList<>();
        List<DeliveryDriver> driver=driverRepository.findAll();

        for (Restaurant restaurant : restaurants) {
            double distance = HelperUtils.calculateDistance(
                    lat,
                    lng,
                    restaurant.getLatitude(),
                    restaurant.getLongitude());

            if (distance <= radiusKm) {
                nearbyRestaurants.add(restaurant);
            }
        }
        return nearbyRestaurants;
    }*/

    public Map<String, Object> getRestaurantAnalytics(Integer restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Double averageRating = reviewRepository.getAverageRatingByRestaurantId(restaurantId);
        if (averageRating == null) {
            averageRating = 0.0;
        }
        List<Orders> orders = orderRepository.findAll();
        List<Orders> completedOrders = orders.stream().filter(o -> o.getRestaurant() != null && restaurantId.equals(o.getRestaurant().getId())).filter(o -> "DELIVERED".equalsIgnoreCase(o.getStatus())).toList();

        long totalCompletedOrders = completedOrders.size();
        double total = orders.stream().filter(o -> o.getRestaurant() != null && o.getRestaurant().getId().equals(restaurantId)).mapToDouble(Orders::getTotalAmount).findFirst().orElse(0.0);


        Map<String, Object> analytics = new HashMap<>();
        analytics.put("restaurantId", restaurantId);
        analytics.put("averageRating", averageRating);
        analytics.put("totalRevenue", total);
        analytics.put("totalCompletedOrders", totalCompletedOrders);
        return analytics;
    }

    public List<MenuItem> getTopSellers(Integer restaurantId) {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        List<MenuItem> topSellers = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.getRestaurant() != null && item.getRestaurant().getId().equals(restaurantId) && item.getIsActive()) {
                topSellers.add(item);
                if (topSellers.size() == 5) {
                    break;
                }
            }
        }
        return topSellers;
    }

    public List<MenuItem> searchMenuItems(String keyword, Integer minCalories, Integer maxCalories) {
        return menuItemRepository.searchCrossRestaurantMenu(keyword, minCalories, maxCalories);
    }
}
