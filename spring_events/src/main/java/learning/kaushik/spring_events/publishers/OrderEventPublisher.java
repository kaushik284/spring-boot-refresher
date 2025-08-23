package learning.kaushik.spring_events.publishers;

import learning.kaushik.spring_events.dto.OrderResponse;
import learning.kaushik.spring_events.events.CreateOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishCreateOrderEvent(OrderResponse order){
        log.info("publishing create order event for orderId: {}", order.orderId());
        CreateOrderEvent createOrderEvent = new CreateOrderEvent(order);
        eventPublisher.publishEvent(createOrderEvent);
    }
}
