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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public OrdersResponseDTO cancelOrder(Integer orderId){
        Orders orders = orderRepository.findById(orderId).get();
        if(orders==null || !orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        } else if("DELIVERED".equalsIgnoreCase(orders.getStatus()) || "CANCELLED".equalsIgnoreCase(orders.getStatus())){
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_UPDATED_STATUS);
        }
        orders.setStatus("CANCELLED");
        Orders updatedOrder=orderRepository.save(orders);
        return OrdersResponseDTO.convertToDTO(updatedOrder);
    }

    public OrdersResponseDTO calculateOrderTotals(Integer orderId){
        Orders orders = orderRepository.findById(orderId).get();
        if(orders==null || !orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        } else if("DELIVERED".equalsIgnoreCase(orders.getStatus()) || "CANCELLED".equalsIgnoreCase(orders.getStatus())){
            throw new InvalidOrderStateException(ErrorMessage.CALCULATE_TOTAL_FOR_DELIVERED_CANCELLED_ITEM);
        }

        double subTotal=0.0;
        if(orders.getOrderItems()!=null){
            for (OrderItem or: orders.getOrderItems()){
                subTotal=subTotal+or.getTotalPrice();
            }
        }
        orders.setSubtotal(subTotal);

        double calculateTotal=HelperUtils.calculateTotal(orders.getSubtotal(),orders.getDeliveryFee(),orders.getDiscountAmount());
        orders.setTotalAmount(calculateTotal);

        Orders updatedOrder=orderRepository.save(orders);

        return OrdersResponseDTO.convertToDTO(updatedOrder);
    }

    public CorporateOrderResponseDTO placeCorporateOrder(CorporateOrderRequestDTO dto){
        if(dto==null){
            throw new InvalidOrderStateException(ErrorMessage.ORDER_REQUEST_EMPTY);
        }
        CorporateOrder corporateOrder=new CorporateOrder();
        corporateOrder.setCorporateCode(HelperUtils.generateCode("CRP"));
        corporateOrder.setOrderDate(LocalDate.now());
        corporateOrder.setStatus("PENDING");
        corporateOrder.setCompanyName(dto.getCompanyName());
        corporateOrder.setCostCenter(dto.getCostCenter());
        corporateOrder.setTotalAmount(dto.getTotalAmount());

        return CorporateOrderResponseDTO.convertToDTO(corporateOrder);
    }

    public List<OrdersResponseDTO> getOrderTimeline(Integer orderId) {
        Orders order = orderRepository.findById(orderId).get();
        if(order==null || !order.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
        List<OrdersResponseDTO> timeline = new ArrayList<>();
        OrdersResponseDTO pendingOrder=new OrdersResponseDTO();
        pendingOrder.setStatus("PENDING");
        pendingOrder.setOrderDate(order.getOrderDate().atStartOfDay().toLocalDate());
        timeline.add(pendingOrder);

        if (order.getStatus().equalsIgnoreCase("DELIVERED")) {
            OrdersResponseDTO deliveredEvent = new OrdersResponseDTO();
            deliveredEvent.setStatus("DELIVERED");
            deliveredEvent.setOrderDate(order.getOrderDate());
            timeline.add(deliveredEvent);
        }
        return timeline;
    }

    public OrdersResponseDTO reorderPastOrder(Integer orderId) {
        Orders pastOrder = orderRepository.findById(orderId).get();
        if(pastOrder==null || !pastOrder.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
        Orders newOrder = new Orders();
        newOrder.setCustomer(pastOrder.getCustomer());
        newOrder.setRestaurant(pastOrder.getRestaurant());
        newOrder.setTotalAmount(pastOrder.getTotalAmount());
        newOrder.setDeliveryFee(pastOrder.getDeliveryFee());
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setStatus("PENDING");
        newOrder.setIsActive(true);

        return OrdersResponseDTO.convertToDTO(orderRepository.save(newOrder));
    }

    public Page<OrdersResponseDTO> getCustomerOrdersFiltered(Integer customerId, String status, LocalDate from, LocalDate to, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Orders> ordersPage = orderRepository.findFilteredOrdersByCustomer(customerId, status, from, to, pageable);

        List<OrdersResponseDTO> dtoList = new ArrayList<>();
        for (Orders order : ordersPage.getContent()) {
            OrdersResponseDTO dto = new OrdersResponseDTO();
            dto.setId(order.getId());
            dto.setStatus(order.getStatus());
            dto.setOrderDate(order.getOrderDate());
            dto.setTotalAmount(order.getTotalAmount());

            if (order.getRestaurant() != null) {
                dto.setId(order.getRestaurant().getId());
            }
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, pageable, ordersPage.getTotalElements());
    }

    public OrdersResponseDTO getOrderETA(Integer orderId) {
        Orders order = orderRepository.findById(orderId).get();
        if(order == null || !order.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }

        int estimatedMinutesRemaining = 25;
        if ("DELIVERED".equalsIgnoreCase(order.getStatus())) {
            estimatedMinutesRemaining = 0;
        }

        OrdersResponseDTO response = new OrdersResponseDTO();
        response.setId(orderId);
        response.setStatus(order.getStatus());
        response.setOrderDate(LocalDate.now());
        response.setEstimatedMinutesRemaining(estimatedMinutesRemaining);

        return response;
    }
}
