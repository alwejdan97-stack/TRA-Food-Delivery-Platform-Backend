package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.CustomerAddressRequest;
import FoodDeliveryPlatform.demo.DTOs.Request.CustomerDTORequest;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerDTOResponse;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerDTORequest customerDTORequest;
    CustomerDTOResponse customerDTOResponse;
    CustomerAddressRequest customerAddressRequest;
    Customer customer;
    public static List<Customer> customers;



    createCustomer(CustomerRequestDTO dto){

    }
    createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO initialAddress){}
    addAddress(Integer customerId, CustomerAddressRequestDTO address){}
    updateLoyaltyPoints(Integer customerId, int points){}
    applyLoyaltyPenalty(Integer customerId, int pointsDeducted){}
    deactivateCustomer(Integer customerId){}
}
