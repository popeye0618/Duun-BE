package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Items;
import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/items/save")
    public void save(@RequestBody Items items){
        itemService.save(items);
    }

    @GetMapping("/items")
    public List<ItemDto>findAllItems(){
        return itemService.findAllItems();
    }
}
