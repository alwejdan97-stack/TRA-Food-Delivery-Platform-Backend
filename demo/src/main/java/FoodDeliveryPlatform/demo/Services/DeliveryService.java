package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.OrdersResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.Orders;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.time.LocalDate;
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
        } else if(driver==null || !driver.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }

        Delivery delivery=new Delivery();
        delivery.setAssignedAt(LocalDateTime.now());
        delivery.setDeliveryDriver(driver);
        delivery.setStatus("DRIVER_ASSIGNED");
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

    public DeliveryResponseDTO updateDriverLocation(Integer driverId, double lat, double lng){
        Optional<DeliveryDriver> driver=deliveryRepository.findById(driverId);
        if(driver.isEmpty() || !driver.get().getIsOnline()){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }

        driver.setCurrentLat(lat);
        driver.setCurrentLng(lng);

        deliveryDriverRepository.save(driver);
    }
•


    markDeliveryPickedUp(Integer deliveryId)
• markDeliveryDelivered(Integer deliveryId)
• getDeliveriesForDriver(Integer driverId, String status)
• toggleDriverOnlineStatus(Integer driverId, boolean isOnline)
}
