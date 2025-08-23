package learning.kaushik.spring_events.service;

import learning.kaushik.spring_events.dao.OrderDao;
import learning.kaushik.spring_events.dto.OrderRequest;
import learning.kaushik.spring_events.dto.OrderResponse;
import learning.kaushik.spring_events.publishers.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao dao;
    private final OrderEventPublisher publisher;

    public OrderResponse createOrder(OrderRequest orderRequest){
        log.info("In OrderService::createOrder");
        OrderResponse order = dao.createOrder(orderRequest);
        log.info("publish event");
        publisher.publishCreateOrderEvent(order);
        return order;
    }

    public List<OrderResponse> getAllOrders() {
        return dao.getOrders();
    }
}
