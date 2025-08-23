package learning.kaushik.spring_events.controller;

import learning.kaushik.spring_events.dto.OrderRequest;
import learning.kaushik.spring_events.dto.OrderResponse;
import learning.kaushik.spring_events.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        log.info("OrderController::Creating order: {}", orderRequest);
        // call a service
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/orders")
    public List<OrderResponse> getOrders(){
        log.info("OrderController::getOrders");
        return orderService.getAllOrders();
    }
}
