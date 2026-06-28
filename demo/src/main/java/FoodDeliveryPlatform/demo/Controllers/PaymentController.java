package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.PaymentResponseDTO;
import FoodDeliveryPlatform.demo.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/processPayment/order/{orderId}?method=CASH")
    public ResponseEntity<PaymentResponseDTO> processPayment(@PathVariable Integer orderId, @RequestParam(name = "method", defaultValue = "CASH") String method) {

        PaymentResponseDTO response = paymentService.processPayment(orderId, method);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getPaymentInfoByOrder/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentInfoByOrder(@PathVariable Integer orderId) {
        PaymentResponseDTO response = paymentService.processPayment(orderId, "CASH");
        return ResponseEntity.ok(response);
    }

    @PutMapping("refundPayment/{paymentId}/refund")
    public ResponseEntity<PaymentResponseDTO> refundPayment(@PathVariable Integer paymentId) {
        PaymentResponseDTO response = paymentService.refundPayment(paymentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("completePayment/{paymentId}/complete")
    public ResponseEntity<PaymentResponseDTO> completePayment(@PathVariable Integer paymentId) {
        PaymentResponseDTO response = paymentService.processPayment(paymentId, "CASH");
        return ResponseEntity.ok(response);
    }

}
