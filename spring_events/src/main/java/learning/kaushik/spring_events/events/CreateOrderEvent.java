package learning.kaushik.spring_events.events;

import learning.kaushik.spring_events.dto.OrderResponse;

public record CreateOrderEvent(OrderResponse order) {
}
