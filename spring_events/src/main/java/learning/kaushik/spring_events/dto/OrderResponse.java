package learning.kaushik.spring_events.dto;

public record OrderResponse(int orderId, int itemId, Item item, int quantity) {
}
