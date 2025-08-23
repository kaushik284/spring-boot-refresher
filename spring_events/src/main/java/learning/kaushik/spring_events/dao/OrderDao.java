package learning.kaushik.spring_events.dao;

import jakarta.annotation.PostConstruct;
import learning.kaushik.spring_events.dto.Item;
import learning.kaushik.spring_events.dto.OrderRequest;
import learning.kaushik.spring_events.dto.OrderResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderDao {

    // this is dummy dao for now
    List<OrderResponse> orderList;

    private final ItemDao itemDao;

    private static Random random = new Random();

    @PostConstruct
    void setup() {
        orderList = new ArrayList<>();
        orderList.addAll(List.of(new OrderResponse(1, 1, itemDao.getItemById(1), 2),
                new OrderResponse(2, 2, itemDao.getItemById(2), 1),
                new OrderResponse(3, 3, itemDao.getItemById(3), 1)));
    }

    public OrderResponse createOrder(OrderRequest request) {
        log.info("in OrderDao::createOrder");
        Item item = itemDao.getItemById(request.itemId());
        OrderResponse orderResponse = new OrderResponse(random.nextInt(4, 10000), request.itemId(),
                item, request.quantity());
        orderList.add(orderResponse);
        return orderResponse;
    }

    public List<OrderResponse> getOrders() {
        return List.copyOf(orderList);
    }
}
