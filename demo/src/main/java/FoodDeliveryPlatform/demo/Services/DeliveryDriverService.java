package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.DeliveryDriverRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryDriverResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.DeliveryResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Delivery;
import FoodDeliveryPlatform.demo.Entities.DeliveryDriver;
import FoodDeliveryPlatform.demo.Repositories.DeliveryDriverRepository;
import FoodDeliveryPlatform.demo.Repositories.DeliveryRepository;
import FoodDeliveryPlatform.demo.Utilities.HelperUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryDriverService {
    DeliveryDriverRepository driverRepository;
    DeliveryRepository deliveryRepository;

    public DeliveryDriverResponseDTO registerDriver(DeliveryDriverRequestDTO request) {
        DeliveryDriver newDriver = new DeliveryDriver();
        newDriver.setFirstName(request.getFirstName());
        newDriver.setLastName(request.getLastName());
        newDriver.setDriverCode(HelperUtils.generateCode("DD-01"));
        newDriver.setIsOnline(false);

        return DeliveryDriverResponseDTO.convertToDTO(driverRepository.save(newDriver));
    }

    public List<DeliveryDriverResponseDTO> getAllDrivers() {
        return DeliveryDriverResponseDTO.convertToDTO(driverRepository.findAll());
    }

    public List<DeliveryDriverResponseDTO> getOnlineDrivers() {
        List<DeliveryDriver> onlineDrivers=driverRepository.getOnlineDrivers(true);
        return DeliveryDriverResponseDTO.convertToDTO(onlineDrivers);
    }

    public DeliveryDriverResponseDTO toggleOnlineStatus(Integer id, boolean isOnline) {

        DeliveryDriver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setIsOnline(isOnline);
        driver.setUpdatedDate(LocalDate.now());
        DeliveryDriver updatedDriver=driverRepository.save(driver);

        return DeliveryDriverResponseDTO.convertToDTO(updatedDriver);
    }

    public DeliveryDriverResponseDTO updateLocation(Integer id, Double lat, Double lng) {
        DeliveryDriver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setCurrentLat(lat);
        driver.setCurrentLng(lng);
        driver.setUpdatedDate(LocalDate.now());
        DeliveryDriver updatedDriver= driverRepository.save(driver);

        return DeliveryDriverResponseDTO.convertToDTO(updatedDriver);
    }

    public DeliveryDriverResponseDTO getDeliveryHistory(Integer id) {
        DeliveryDriver driver= deliveryRepository.findById(id).get();

        return DeliveryDriverResponseDTO.convertToDTO(driver);
    }

    public List<DeliveryDriverResponseDTO> getActiveDelivery(Integer id) {
        List<DeliveryDriver> drivers=deliveryRepository.findByDeliveryDriverIdAndStatus(id,"IN_PROGRESS");
        List<DeliveryDriverResponseDTO> responseDTOS=new ArrayList<>();

        for(DeliveryDriver dd:drivers){
            DeliveryDriverResponseDTO dto=DeliveryDriverResponseDTO.convertToDTO(dd);
            responseDTOS.add(dto);
        }
        return responseDTOS;
    }
}
