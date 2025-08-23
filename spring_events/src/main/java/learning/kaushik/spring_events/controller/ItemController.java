package learning.kaushik.spring_events.controller;

import learning.kaushik.spring_events.dto.Item;
import learning.kaushik.spring_events.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        Item newItem = itemService.addItem(item);
        return ResponseEntity.ok(newItem);
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable int itemId){
        log.info("getting item for itemId: {}", itemId);
        Item found = itemService.getItemById(itemId);
        return ResponseEntity.ok(found);
    }

}
