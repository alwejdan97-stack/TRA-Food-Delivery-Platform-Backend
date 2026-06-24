package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.CustomerAddressRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.CustomerRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerRequestDTO customerDTORequest;
    CustomerResponseDTO customerDTOResponse;
    CustomerAddressRequestDTO customerAddressRequest;
    Customer customer;
    public static List<Customer> customers;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerRequestDTO customerDTORequest, CustomerResponseDTO customerDTOResponse, CustomerAddressRequestDTO customerAddressRequest, Customer customer) {
        this.customerRepository = customerRepository;
        this.customerDTORequest = customerDTORequest;
        this.customerDTOResponse = customerDTOResponse;
        this.customerAddressRequest = customerAddressRequest;
        this.customer = customer;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto){
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPassword());

        Customer newCustomer=customerRepository.save(customer);
        customerDTOResponse.setFirstName(newCustomer.getFirstName());
        customerDTOResponse.setLastName(newCustomer.getLastName());
        customerDTOResponse.setPhone(newCustomer.getPhone());
        customerDTOResponse.setEmail(newCustomer.getEmail());
        customerDTOResponse.setPassword(newCustomer.getPasswordHash());

        return customerDTOResponse;
    }
    createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO initialAddress){}
    addAddress(Integer customerId, CustomerAddressRequestDTO address){}
    updateLoyaltyPoints(Integer customerId, int points){}
    applyLoyaltyPenalty(Integer customerId, int pointsDeducted){}
    deactivateCustomer(Integer customerId){}
}
