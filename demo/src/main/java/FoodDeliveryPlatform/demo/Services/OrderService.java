package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.CorporateOrderRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.OrderItemRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.OrdersRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.OrdersResponseDTO;
import FoodDeliveryPlatform.demo.Entities.*;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import FoodDeliveryPlatform.demo.Repositories.MenuItemRepository;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;
    MenuItemRepository menuItemRepository;
    CustomerRepository customerRepository;
    RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository, CustomerRepository customerRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<OrdersResponseDTO> createOrder(Integer customerId, Integer restaurantId, List<OrderItemRequestDTO> items){
        Optional<Customer> customers=customerRepository.findById(customerId);
        Optional<Restaurant> restaurants=restaurantRepository.findById(restaurantId);
        if(customers.isEmpty() || !customers.get().getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        if(restaurants.isEmpty() || !restaurants.get().getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Customer customer=customers.get();
        Restaurant restaurant=restaurants.get();
        Orders order=new Orders();
        order.setRestaurant(restaurant);
        order.setCustomer(customer);
        order.setStatus("PENDING");
        order.setUpdatedDate(LocalDate.now());

        List<OrderItem> orderItems=new ArrayList<>();
        double totalAmount=0.0;

        for(OrderItemRequestDTO oi:items){
            MenuItem menuItem = menuItemRepository.findById(oi.getId()).get();
            if(menuItem==null || !menuItem.getIsActive()){
                throw new ResourceNotFoundException(ErrorMessage.ITEM_NOT_FOUND);
            }
            OrderItem orderItem=new OrderItem();
            orderItem.setCorporateOrder();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(oi.getQuantity());
            orderItem.setUnitPrice(menuItem.getPrice());

            totalAmount= totalAmount+menuItem.getPrice()*oi.getQuantity();

            orderItems.add(orderItem);
        }
        totalAmount=totalAmount+restaurant.getDeliveryFee();
        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        Orders orderToSave=orderRepository.save(order);

        List<OrdersResponseDTO> ordersResponseDTOS=new ArrayList<>();
        ordersResponseDTOS.add(OrdersResponseDTO.convertToDTO(orderToSave));
        return ordersResponseDTOS;
    }

    public List<OrdersResponseDTO> createOrder(Integer customerId, Integer restaurantId, List<OrderItemRequestDTO> items, String notes){
        Optional<Customer> customers=customerRepository.findById(customerId);
        Optional<Restaurant> restaurants=restaurantRepository.findById(restaurantId);
        if(customers.isEmpty() || !customers.get().getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        if(restaurants.isEmpty() || !restaurants.get().getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
        }
        Customer customer=customers.get();
        Restaurant restaurant=restaurants.get();
        Orders order=new Orders();
        order.setRestaurant(restaurant);
        order.setCustomer(customer);
        order.setStatus("PENDING");
        order.setUpdatedDate(LocalDate.now());

        List<OrderItemRequestDTO> orderItemRequestDTOS=new ArrayList<>();
        double amount=0.0;
    }

    public List<MenuItemResponseDTO> addMenuItemToOrder(Integer orderId, Integer menuItemId, int quantity)

    public List<MenuItemResponseDTO> removeMenuItemFromOrder(Integer orderId, Integer orderItemId)

    public applyDiscount(Integer orderId, double discountAmount)

    updateOrderStatus(Integer orderId, String newStatus)

    cancelOrder(Integer orderId){}

    calculateOrderTotals(Integer orderId)

    placeCorporateOrder(CorporateOrderRequestDTO dto)

}
