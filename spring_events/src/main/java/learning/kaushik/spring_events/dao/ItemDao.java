package learning.kaushik.spring_events.dao;

import jakarta.annotation.PostConstruct;
import learning.kaushik.spring_events.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ItemDao {

    // dummy dao
    List<Item> items = new ArrayList<>();

    @PostConstruct
    void setup(){
        items.addAll(List.of(
                new Item(1, "sd-card128GB", 1000.00, 50),
                new Item(2, "mouse-rgb", 1200.50, 20),
                new Item(3, "headphone-wireless", 2500.00, 40)
        ));
    }

    public Item getItemById(int itemId){
        Item foundItem = items.stream().filter(item -> item.itemId() == itemId)
                .findFirst().orElse(null);
        log.info("Found item: {}", foundItem);
        return foundItem;
    }

    public Item updateStock(int itemId, int quantitySold){
        log.info("update Item {} for quantity sold {}", itemId, quantitySold);
        Item foundItem = getItemById(itemId);
        if(foundItem !=null){
            items.remove(foundItem);
            foundItem = new Item(foundItem.itemId(), foundItem.itemName(), foundItem.price(), foundItem.availableQuantity() - quantitySold);
            items.add(foundItem);
            log.info("updated stock for item: {}", foundItem);
        }
        return foundItem;
    }

    public Item addItem(Item item) {
        items.add(item);
        return item;
    }

    public List<Item> getAllItems() {
        return List.copyOf(items);
    }
}
