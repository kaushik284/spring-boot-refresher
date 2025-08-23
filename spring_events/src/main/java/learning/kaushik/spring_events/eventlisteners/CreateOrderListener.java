package learning.kaushik.spring_events.eventlisteners;

import learning.kaushik.spring_events.dao.ItemDao;
import learning.kaushik.spring_events.events.CreateOrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderListener {
    private final ItemDao itemDao;

    @EventListener
    public void createOrderHandler(CreateOrderEvent createOrderEvent){
        log.info("Create order event received: {}", createOrderEvent);
        // do some stuff
        log.info("updating stock");
        itemDao.updateStock(createOrderEvent.order().itemId(), createOrderEvent.order().quantity());
    }
}
