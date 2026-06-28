package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Entities.Orders;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.InvalidOrderStateException;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.DeliveryDriverRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;
    OrderRepository orderRepository;
    DeliveryDriverRepository driverRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository=orderRepository;
    }

    public List<DeliveryDriverResponseDTO> assignDriverToOrder(Integer orderId, Integer driverId){
        Orders orders=orderRepository.findById(orderId).get();
        DeliveryDriver driver=driverRepository.findById(driverId).get();

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

    public DeliveryResponseDTO autoAssignDriver(Integer orderId){
        Orders orders=orderRepository.findById(orderId).get();
        if (orders==null || !orders.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
        List<DeliveryDriver> availableDrivers=driverRepository.findAll();
        DeliveryDriver onlineDrivers= null;
        for(DeliveryDriver dd:availableDrivers){
            if(dd.getIsOnline() && dd.getIsActive()){
                onlineDrivers=dd;
            }
        }
        if(onlineDrivers==null){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }
        Delivery delivery=new Delivery();
        delivery.setAssignedAt(LocalDateTime.now());
        delivery.setStatus("DRIVER_ASSIGNED");
        delivery.setDeliveryDriver(onlineDrivers);
        delivery.setOrders(orders);
        delivery.setTrackingCode(HelperUtils.generateCode("DEL-AS"));

        orders.setDelivery(delivery);
        orderRepository.save(orders);

        return DeliveryResponseDTO.convertToDTO(delivery);
    }

    public DeliveryDriverResponseDTO updateDriverLocation(Integer driverId, double lat, double lng){
        DeliveryDriver driver=driverRepository.findById(driverId).get();
        if(driver==null || !driver.getIsOnline()){
            throw new ResourceNotFoundException(ErrorMessage.DRIVER_NOT_FOUND);
        }
        driver.setCurrentLat(lat);
        driver.setCurrentLng(lng);
        DeliveryDriver updatedDriver=driverRepository.save(driver);

        return DeliveryDriverResponseDTO.convertToDTO(updatedDriver);
    }

    public List<DeliveryResponseDTO> markDeliveryPickedUp(Integer deliveryId){
        Orders orders=orderRepository.findById(deliveryId).get();
        if (orders==null || !orders.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.DELIVERY_NOT_FOUND);
        }
        Delivery delivery=orders.getDelivery();
        if (delivery==null) {
            throw new ResourceNotFoundException(ErrorMessage.DELIVERY_NOT_FOUND);
        }
        if (!"ASSIGNED".equalsIgnoreCase(delivery.getStatus())) {
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_DELIVERY_STATUS);
        }
        delivery.setStatus("PICKED_UP");
        delivery.setPickedUpAt(LocalDateTime.now());
        orderRepository.save(orders);

        List<DeliveryResponseDTO> responseList = new ArrayList<>();
        responseList.add(DeliveryResponseDTO.convertToDTO(delivery));
        return responseList;
    }

    public List<DeliveryResponseDTO> markDeliveryDelivered(Integer deliveryId) {
        Orders orders=orderRepository.findById(deliveryId).get();
        if (orders==null || !orders.getIsActive()) {
            throw new ResourceNotFoundException(ErrorMessage.DELIVERY_NOT_FOUND);
        }
        Delivery delivery=orders.getDelivery();
        if (delivery==null || !"PICKED_UP".equalsIgnoreCase(delivery.getStatus())) {
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_DELIVERY_STATUS);
        }

        delivery.setStatus("DELIVERED");
        delivery.setDeliveredAt(LocalDateTime.now());
        orderRepository.save(orders);

        List<DeliveryResponseDTO> responseList = new ArrayList<>();
        responseList.add(DeliveryResponseDTO.convertToDTO(delivery));
        return responseList;
        }

    public List<DeliveryResponseDTO> getDeliveriesForDriver(Integer driverId, Boolean status){
        List<DeliveryDriver> driveres= driverRepository.findByIdAndIsOnline(driverId,status);
        if (driveres.isEmpty()) {
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_DELIVERY_STATUS);
        }
        List<DeliveryResponseDTO> responseDTOS = new ArrayList<>();
        DeliveryDriver targetDriver=driveres.get(0);
        if(targetDriver.getDeliveries()!=null){
            for (Delivery d : targetDriver.getDeliveries()) {
                if (d != null) {
                    responseDTOS.add(DeliveryResponseDTO.convertToDTO(d));
                }
            }
        }
        return responseDTOS;
    }

    public void toggleDriverOnlineStatus(Integer driverId, boolean isOnline){
        DeliveryDriver driver = driverRepository.findById(driverId).get();
        if (driver==null) {
            throw new InvalidOrderStateException(ErrorMessage.MATCHING_DELIVERY_STATUS);
        }
        driver.setIsOnline(isOnline);
        driverRepository.save(driver);
    }

}
