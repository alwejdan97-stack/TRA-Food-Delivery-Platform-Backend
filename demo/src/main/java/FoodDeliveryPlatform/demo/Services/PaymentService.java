package FoodDeliveryPlatform.demo.Services;

import FoodDeliveryPlatform.demo.DTOs.Response.PaymentResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Orders;
import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Exceptions.ErrorMessage;
import FoodDeliveryPlatform.demo.Exceptions.ResourceNotFoundException;
import FoodDeliveryPlatform.demo.Repositories.OrderRepository;
import FoodDeliveryPlatform.demo.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        Orders updatedOrder=orderRepository.save(orders);

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
        Orders updatedOrder=orderRepository.save(orders);

        return PaymentResponseDTO.convertToDTO(updatedPayment);
    }
}
