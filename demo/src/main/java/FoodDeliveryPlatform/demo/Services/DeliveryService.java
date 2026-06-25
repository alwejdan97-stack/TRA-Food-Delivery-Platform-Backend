package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.Orders;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.InvalidOrderStateException;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;
    OrderRepository orderRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository=orderRepository;
    }

    public List<DeliveryDriverResponseDTO> assignDriverToOrder(Integer orderId, Integer driverId){
        Orders orders=orderRepository.findById(orderId).get();
        DeliveryDriver driver=deliveryRepository.findById(driverId).get();

        if (orders==null || !orders.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        } else if(driver==null || !driver.getIsActive() || driver.getIsOnline()){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }

        Delivery delivery=new Delivery();
        delivery.setAssignedAt(LocalDateTime.now());
        delivery.setDeliveryDriver(driver);
        delivery.setStatus("DRIVER_ASSIGNED");
        delivery.setOrders(orders);
        delivery.setTrackingCode(HelperUtils.generateCode("DEL-D"));

        DeliveryDriver newDriver = new DeliveryDriver();
        newDriver.setId(driverId);
        delivery.setDeliveryDriver(newDriver);

        List<DeliveryDriverResponseDTO> responseDTOS=new ArrayList<>();
        responseDTOS.add(DeliveryDriverResponseDTO.convertToDTO(newDriver));
        return responseDTOS;
    }

    /*public DeliveryResponseDTO autoAssignDriver(Integer orderId){
        Orders orders=orderRepository.findById(orderId).get();
        DeliveryDriver driver=deliveryRepository.findById(D)
        if (orders==null || !orders.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }

        DeliveryDriver closeDriver=null;
        double shortDistance=Double.MAX_VALUE;

        for (DeliveryDriver driver : onlineDrivers) {
            double distance = HelperUtils.calculateDistance(
                    order.getRestaurantLat(), order.getRestaurantLng(),
                    driver.getCurrentLat(), driver.getCurrentLng()
            );

            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestDriver = driver;
            }
        }


    }*/

    public DeliveryDriverResponseDTO updateDriverLocation(Integer driverId, double lat, double lng){
        DeliveryDriver driver=deliveryRepository.findById(driverId).get();
        if(driver==null || !driver.getIsOnline()){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }
        driver.setCurrentLat(lat);
        driver.setCurrentLng(lng);
        DeliveryDriver updatedDriver=deliveryRepository.save(driver);

        return DeliveryDriverResponseDTO.convertToDTO(updatedDriver);
    }

    public List<DeliveryResponseDTO> markDeliveryPickedUp(Integer deliveryId){
        Optional<Delivery> deliveries= deliveryRepository.findById(deliveryId);
        if (deliveries.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessage.DELIVERY_NOT_FOUND);
        }
        Delivery delivery =deliveries.get();

        if (!"ASSIGNED".equalsIgnoreCase(delivery.getStatus())) {
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_DELIVERY_STATUS);
        }
        delivery.setStatus("PICKED_UP");
        delivery.setPickedUpAt(LocalDateTime.now());

        Delivery updatedDelivery = deliveryRepository.save(delivery);

        List<DeliveryResponseDTO> responseList = new ArrayList<>();
        responseList.add(DeliveryResponseDTO.convertToDTO(updatedDelivery));
        return responseList;
        }
    }

    public List<DeliveryResponseDTO> markDeliveryDelivered(Integer deliveryId) {
        Optional<Delivery> deliveries= deliveryRepository.findById(deliveryId);
        if (deliveries.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessage.DELIVERY_NOT_FOUND);
        }
        Delivery delivery =deliveries.get();

        if (!"PICKED_UP".equalsIgnoreCase(delivery.getStatus())) {
            throw new InvalidOrderStateException("Delivery cannot be marked delivered. Current status is: " + delivery.getStatus());
        }

        delivery.setStatus("DELIVERED");
        delivery.setDeliveredAt(LocalDateTime.now());

        Delivery updatedDelivery = deliveryRepository.save(delivery);

        List<DeliveryResponseDTO> responseList = new ArrayList<>();
        responseList.add(DeliveryResponseDTO.convertToDTO(updatedDelivery));
        return responseList;
    }


• getDeliveriesForDriver(Integer driverId, String status)
• toggleDriverOnlineStatus(Integer driverId, boolean isOnline)


}
