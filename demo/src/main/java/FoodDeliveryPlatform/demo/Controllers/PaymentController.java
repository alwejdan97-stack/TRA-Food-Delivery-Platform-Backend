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

    @PostMapping("/order/{orderId}?method=CASH")
    public ResponseEntity<PaymentResponseDTO> processPayment(@PathVariable Integer orderId, @RequestParam(name = "method", defaultValue = "CASH") String method) {

        PaymentResponseDTO response = paymentService.processPayment(orderId, method);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")

    @PutMapping("/{paymentId}/refund")

    @PutMapping("/{paymentId}/complete")

}
