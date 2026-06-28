/*
package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryDriverRepository;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportingService {
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    DeliveryDriverRepository driverRepository;
    RestaurantRepository restaurantRepository;

    @Autowired
    public ReportingService(OrderRepository orderRepository, CustomerRepository customerRepository, DeliveryDriverRepository driverRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Map<String, Object> getRestaurantRevenueByDay(Integer restaurantId, LocalDate date) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Double dailyRevenue = orderRepository.sumRevenueByRestaurantIdAndDate(restaurantId, date);
        if (dailyRevenue == null) {
            dailyRevenue = 0.0;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("restaurantId", restaurantId);
        response.put("date", date);
        response.put("dailyRevenue", dailyRevenue);
        return response;
    }

    public Map<String, Object> getLifetimeOrdersCount(Integer restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        int lifetimeOrders = orderRepository.countCompletedOrdersByRestaurantId(restaurantId,"DELIVERED");

        Map<String, Object> response = new HashMap<>();
        response.put("restaurantId", restaurantId);
        response.put("lifetimeOrders", lifetimeOrders);
        return response;
    }

    public List<CustomerResponseDTO> getTopLoyaltyCustomers() {
        List<Customer> topCustomers = customerRepository.findAll();
        List<CustomerResponseDTO> responseDTOs = new ArrayList<>();

        for (Customer customer : topCustomers) {
            responseDTOs.add(CustomerResponseDTO.convertToDTO(customer));
        }
        return responseDTOs;
    }

    public List<DeliveryDriverResponseDTO> getDriversLeaderboard() {
        List<DeliveryDriver> topDrivers = driverRepository.findAll();
        List<DeliveryDriverResponseDTO> responseDTOs = new ArrayList<>();

        for (DeliveryDriver driver : topDrivers) {
            responseDTOs.add(DeliveryDriverResponseDTO.convertToDTO(driver));
        }
        return responseDTOs;
    }

    public Map<String, Object> getPlatformDailySummary(LocalDate date) {
        int totalOrdersCount = orderRepository.countByCreatedDate(date);
        Double totalDeliveryFees = orderRepository.sumDeliveryFeesByDate(date);
        Double totalPlatformRevenue = orderRepository.sumTotalAmountDeliveredOrdersOnSpecifyDate(date);

        Map<String, Object> response = new HashMap<>();
        response.put("date", date);
        response.put("totalOrdersCount", totalOrdersCount);
        response.put("totalDeliveryFeesCollected", totalDeliveryFees != null ? totalDeliveryFees : 0.0);
        response.put("totalPlatformRevenue", totalPlatformRevenue != null ? totalPlatformRevenue : 0.0);
        return response;
    }

}
*/
