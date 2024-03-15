package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/item/{id}")
    public Item findItem(@PathVariable Long id) {
        return itemService.findItem(id);
    }

    @PostMapping("/item/save")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PutMapping("/item/update/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/item/{id}")
    public String removeItem(@PathVariable Long id) {
        itemService.removeItem(id);
        return "remove Successfully";
    }
}
