package FoodDeliveryPlatform.demo.Controllers;

import FoodDeliveryPlatform.demo.DTOs.Response.PaymentResponseDTO;
import FoodDeliveryPlatform.demo.Entities.Payment;
import FoodDeliveryPlatform.demo.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/refundPayment/{paymentId}/refund")
    public ResponseEntity<PaymentResponseDTO> refundPayment(@PathVariable Integer paymentId) {
        PaymentResponseDTO response = paymentService.refundPayment(paymentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/completePayment/{paymentId}/complete")
    public ResponseEntity<PaymentResponseDTO> completePayment(@PathVariable Integer paymentId) {
        PaymentResponseDTO response = paymentService.processPayment(paymentId, "CASH");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFilteredPayments")
    public ResponseEntity<Page<PaymentResponseDTO>> getFilteredPayments(
            @RequestParam(name = "method", required = false) String method,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(paymentService.getFilteredPayments(method, status, from, to, page, size));
    }

    @GetMapping("/getAnalyticsByMethod/analytics/by-method")
    public ResponseEntity<List<PaymentResponseDTO>> getAnalyticsByMethod() {
        return ResponseEntity.ok(paymentService.getAmountProcessedGroupedByMethod());
    }
}
