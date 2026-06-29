package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.PaymentResponseDTO;
import FoodDeliveryPlatform.demo.DTOs.Response.ReviewResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Orders;
import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Entities.Review;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    OrderRepository orderRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository=orderRepository;
    }

    public PaymentResponseDTO processPayment(Integer orderId, String method){
        Orders orders=orderRepository.findById(orderId).get();
        if(!orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
        Payment payment=orders.getPayment();
        if (payment == null) {
            payment = new Payment();
        }
        payment.setPaymentMethod(method);
        payment.setProcessedAt(LocalDateTime.now());
        payment.setStatus("COMPLETED");
        payment.setAmount(orders.getTotalAmount());
        Payment newPayment=paymentRepository.save(payment);

        orders.setStatus("PAID");
        orders.setPayment(newPayment);
        orderRepository.save(orders);

        return PaymentResponseDTO.convertToDTO(newPayment);
    }

    public PaymentResponseDTO refundPayment(Integer orderId){
        Orders orders=orderRepository.findById(orderId).get();
        if(!orders.getIsActive()){
            throw new ResourceNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
        if (!"PAID".equalsIgnoreCase(orders.getStatus()) && !"DELIVERED".equalsIgnoreCase(orders.getStatus())) {
            throw new ResourceNotFoundException(ErrorMessage.PAYMENT_FAILED);
        }
        Payment payment=orders.getPayment();
        if (payment == null) {
            throw new ResourceNotFoundException(ErrorMessage.PAYMENT_NOT_FOUND);
        }
        payment.setStatus("REFUNDED");
        Payment updatedPayment=paymentRepository.save(payment);

        orders.setStatus("REFUNDED");
        orderRepository.save(orders);

        return PaymentResponseDTO.convertToDTO(updatedPayment);
    }

    public Page<PaymentResponseDTO> getFilteredPayments(String method, String status, LocalDate from, LocalDate to, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> paymentPage = paymentRepository.findFilteredPayments(method, status, from, to, pageable);
        List<PaymentResponseDTO> dtos = new ArrayList<>();
        for (Payment p : paymentPage.getContent()) {
            dtos.add(PaymentResponseDTO.convertToDTO(p));
        }
        return new PageImpl<>(dtos, pageable, paymentPage.getTotalElements());
    }


    public List<PaymentResponseDTO> getAmountProcessedGroupedByMethod() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentResponseDTO> responseDTOS=new ArrayList<>();
        for (Payment payment: payments) {
            if (payment.getStatus().equalsIgnoreCase("SUCCESS")) {
                String method = payment.getPaymentMethod();
                double amount=payment.getAmount();
                PaymentResponseDTO currentMethod=null;
                for(PaymentResponseDTO dto:responseDTOS){
                    if(dto.getPaymentMethod().equalsIgnoreCase(method)){
                    currentMethod=dto;
                    }
                }
                if(currentMethod!=null){
                    currentMethod.setAmount(currentMethod.getAmount()+amount);
                }
                PaymentResponseDTO newDto = new PaymentResponseDTO();
                newDto.setPaymentMethod(method);
                newDto.setAmount(amount);
                responseDTOS.add(newDto);
            }
        }
        return responseDTOS;
    }
}
