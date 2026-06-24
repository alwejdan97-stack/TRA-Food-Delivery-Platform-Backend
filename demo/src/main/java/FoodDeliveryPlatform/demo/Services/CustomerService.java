package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Request.CustomerAddressRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Request.CustomerRequestDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.CustomerResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Customer;
import FoodDeliveryPlatform.demo.Entities.CustomerAddress;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerRequestDTO customerRequestDTO;
    CustomerResponseDTO customerResponseDTO;
    CustomerAddressRequestDTO customerAddressRequestDTO;
    Customer customer;
    CustomerAddress customerAddress;
    public static List<Customer> customers;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerRequestDTO customerDTORequest, CustomerResponseDTO customerDTOResponse, CustomerAddressRequestDTO customerAddressRequest, Customer customer, CustomerAddress customerAddress) {
        this.customerRepository = customerRepository;
        this.customerRequestDTO = customerDTORequest;
        this.customerResponseDTO = customerDTOResponse;
        this.customerAddressRequestDTO = customerAddressRequest;
        this.customer = customer;
        this.customerAddress=customerAddress;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto){
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPassword());

        Customer newCustomer=customerRepository.save(customer);

        //convert customer to CustomerResponseDTO
        /*customerResponseDTO.setId(newCustomer.getId());
        customerResponseDTO.setFirstName(newCustomer.getFirstName());
        customerResponseDTO.setLastName(newCustomer.getLastName());
        customerResponseDTO.setPhone(newCustomer.getPhone());
        customerResponseDTO.setEmail(newCustomer.getEmail());
        customerResponseDTO.setPassword(newCustomer.getPasswordHash());*/

        return CustomerResponseDTO.convertToDTO(newCustomer);
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto, CustomerAddressRequestDTO initialAddress){
        //adding customer
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setPasswordHash(dto.getPassword());

        //adding address
        customerAddress.setId(initialAddress.getId());
        customerAddress.setStreet(initialAddress.getStreet());
        customerAddress.setCity(initialAddress.getCity());
        customerAddress.setBuilding(initialAddress.getBuilding());
        customerAddress.setCustomer(customer);

        //assign address to customer object
        customer.getCustomerAddresses().add(customerAddress);

        Customer newCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(newCustomer);
    }

    public CustomerResponseDTO addAddress(Integer customerId, CustomerAddressRequestDTO address){
        if(customers.isEmpty() || !customerRepository.existsById(customerId)){
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get(customerId);

        customerAddress.setId(address.getId());
        customerAddress.setStreet(address.getStreet());
        customerAddress.setCity(address.getCity());
        customerAddress.setBuilding(address.getBuilding());
        customerAddress.setCustomer(customer);

        customer.getCustomerAddresses().add(customerAddress);
        customer.setUpdatedDate(LocalDate.now());

        Customer newCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(newCustomer);
    }

    public CustomerResponseDTO updateLoyaltyPoints(Integer customerId, int points){
        if (customers.isEmpty() || !customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get(customerId);

        customer.setLoyaltyPoints(customer.getLoyaltyPoints()+ points);
        customer.setUpdatedDate(LocalDate.now());

        Customer newCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(newCustomer);
    }
    public CustomerResponseDTO applyLoyaltyPenalty(Integer customerId, int pointsDeducted){
        if (customers.isEmpty() || !customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get(customerId);
        int existPoint=customer.getLoyaltyPoints();
        customer.setLoyaltyPoints(Math.max(0,existPoint-pointsDeducted));

        customer.setUpdatedDate(LocalDate.now());

        Customer newCustomer=customerRepository.save(customer);

        return CustomerResponseDTO.convertToDTO(newCustomer);
    }

    public void deactivateCustomer(Integer customerId){
        if (customers.isEmpty() || !customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
        }
        Customer customer=customers.get(customerId);

        customer.setIsActive(false);

        customer.setUpdatedDate(LocalDate.now());

        Customer newCustomer=customerRepository.save(customer);
    }
}
