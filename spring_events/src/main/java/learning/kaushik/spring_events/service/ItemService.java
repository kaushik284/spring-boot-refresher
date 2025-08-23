package learning.kaushik.spring_events.service;

import learning.kaushik.spring_events.dao.ItemDao;
import learning.kaushik.spring_events.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemDao itemDao;

    public Item addItem(Item item){
        log.info("adding item {}", item);
        return itemDao.addItem(item);
    }

    public List<Item> getAllItems() {
        log.info("get all items");
        return itemDao.getAllItems();
    }

    public Item getItemById(int itemId) {
        log.info("get item by Id: {}", itemId);
        return itemDao.getItemById(itemId);
    }
}
