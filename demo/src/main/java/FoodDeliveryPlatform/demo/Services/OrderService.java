package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.CorporateOrderRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.OrderItemRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.OrdersRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CorporateOrderResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.MenuItemResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.OrdersResponseDTO;
import FoodDeliveryPlatform.demo.Entities.*;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.InvalidOrderStateException;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import FoodDeliveryPlatform.demo.Repositories.MenuItemRepository;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Repositories.RestaurantRepository;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
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
            orderItem.setOrders(order);
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
        order.setDeliveryNotes(notes);
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
            orderItem.setOrders(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(oi.getQuantity());
            orderItem.setUnitPrice(menuItem.getPrice());

            totalAmount= totalAmount+menuItem.getPrice()*oi.getQuantity();

            orderItems.add(orderItem);
        }
        totalAmount= HelperUtils.calculateTotal(totalAmount,order.getRestaurant().getDeliveryFee());
        //totalAmount=totalAmount+restaurant.getDeliveryFee();
        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        Orders orderToSave=orderRepository.save(order);

        List<OrdersResponseDTO> ordersResponseDTOS=new ArrayList<>();
        ordersResponseDTOS.add(OrdersResponseDTO.convertToDTO(orderToSave));
        return ordersResponseDTOS;
    }

    public List<MenuItemResponseDTO> addMenuItemToOrder(Integer orderId, Integer menuItemId, int quantity){
        MenuItem menuItem = menuItemRepository.findById(menuItemId).get();
        Orders orders = orderRepository.findById(orderId).get();

        if(menuItem==null|| !menuItem.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ITEM_NOT_FOUND);
        }else if(orders==null || !orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }else if(quantity<=0){
            throw new IllegalArgumentException(ErrorMessage.INVALID_QUANTITY);
        }
        /*MenuItem menuItem=menuItems.get();
        Orders order=orders.get();*/
        String status="PENDING";
        if(!status.equalsIgnoreCase(orders.getStatus())){
            throw new IllegalStateException(ErrorMessage.ORDER_STATUS);
        }
        boolean itemAlreadyOrdered=false;
        for(OrderItem oi: orders.getOrderItems()){
            if(oi.getMenuItem().getId().equals(menuItemId)){
                oi.setQuantity(oi.getQuantity()+quantity);
                itemAlreadyOrdered=true;
                break;
            }
        }
        if(!itemAlreadyOrdered){
            OrderItem newOrderItem=new OrderItem();
            newOrderItem.setMenuItem(menuItem);
            newOrderItem.setOrders(orders);
            newOrderItem.setQuantity(quantity);
            newOrderItem.setUnitPrice(menuItem.getPrice());

            orders.getOrderItems().add(newOrderItem);
        }
        double currentTotal = 0.0;
        for (OrderItem item : orders.getOrderItems()) {
            currentTotal = currentTotal+ item.getUnitPrice() * item.getQuantity();
        }
        currentTotal= HelperUtils.calculateTotal(currentTotal,orders.getRestaurant().getDeliveryFee());
        //currentTotal += order.getRestaurant().getDeliveryFee();
        orders.setTotalAmount(currentTotal);
        orders.setUpdatedDate(LocalDate.now());

        orderRepository.save(orders);
        List<MenuItemResponseDTO> menuItemResponseDTOS=new ArrayList<>();
        for (OrderItem item : orders.getOrderItems()) {
            menuItemResponseDTOS.add(MenuItemResponseDTO.convertToDTO(item.getMenuItem()));
        }
        return menuItemResponseDTOS;
    }

    public List<MenuItemResponseDTO> removeMenuItemFromOrder(Integer orderId, Integer orderItemId){
        Orders orders = orderRepository.findById(orderId).get();
        if(orders==null || !orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
        //Orders order=orders.get();
        OrderItem orderToRemove=null;
        for(OrderItem or:orders.getOrderItems()){
            if(or.getId().equals(orderItemId)){
                orderToRemove=or;
                break;
            }
        }
        orders.getOrderItems().remove(orderToRemove);
        Orders updatedOrder=orderRepository.save(orders);

        List<MenuItemResponseDTO> menuItemResponseDTOS=new ArrayList<>();
        for(OrderItem oi:updatedOrder.getOrderItems()){
            MenuItem menuItem=oi.getMenuItem();
            MenuItemResponseDTO menuItemResponse=new MenuItemResponseDTO();
            menuItemResponse.setId(menuItem.getId());
            menuItemResponse.setName(menuItem.getName());
            menuItemResponse.setPrice(menuItem.getPrice());
            menuItemResponse.setDescription(menuItem.getDescription());

            menuItemResponseDTOS.add(menuItemResponse);
        }

        if(orderToRemove==null){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }

        return menuItemResponseDTOS;
    }

    public List<MenuItemResponseDTO> applyDiscount(Integer orderId, double discountAmount){
        Orders orders = orderRepository.findById(orderId).get();
        if(orders==null || !orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        } else if("DELIVERED".equalsIgnoreCase(orders.getStatus()) || "CANCELLED".equalsIgnoreCase(orders.getStatus())){
            throw new InvalidOrderStateException(ErrorMessage.DELIVERED_CANCELLED_ITEM);
        }else if(discountAmount>orders.getSubtotal()){
            throw new InvalidOrderStateException(ErrorMessage.DISCOUNT_AMOUNT_GRATER_THAN_SUBTOTAL);
        }

        double updatedTotal=HelperUtils.calculateTotal(orders.getSubtotal(),orders.getDeliveryFee(),orders.getDiscountAmount());
        orders.setTotalAmount(updatedTotal);
        Orders updatedOrder=orderRepository.save(orders);

        List<MenuItemResponseDTO> menuItemResponseDTOS=new ArrayList<>();
        for(OrderItem oi:updatedOrder.getOrderItems()){
            MenuItem menuItem=oi.getMenuItem();
            MenuItemResponseDTO menuItemResponse=new MenuItemResponseDTO();
            menuItemResponse.setId(menuItem.getId());
            menuItemResponse.setName(menuItem.getName());
            menuItemResponse.setPrice(menuItem.getPrice());
            menuItemResponse.setDescription(menuItem.getDescription());

            menuItemResponseDTOS.add(menuItemResponse);
        }

        return menuItemResponseDTOS;
    }

    public OrdersResponseDTO updateOrderStatus(Integer orderId, String newStatus){
        Orders orders = orderRepository.findById(orderId).get();
        if(orders==null || !orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        } else if("DELIVERED".equalsIgnoreCase(newStatus) || "CANCELLED".equalsIgnoreCase(newStatus)){
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_UPDATED_STATUS);
        }
        orders.setStatus(newStatus);
        Orders updatedOrder=orderRepository.save(orders);
        return OrdersResponseDTO.convertToDTO(updatedOrder);
    }

    public OrdersResponseDTO cancelOrder(Integer orderId){}

    public OrdersResponseDTO calculateOrderTotals(Integer orderId){}

    public CorporateOrderResponseDTO placeCorporateOrder(CorporateOrderRequestDTO dto){}

}
