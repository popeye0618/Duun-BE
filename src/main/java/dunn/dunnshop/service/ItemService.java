package dunn.dunnshop.service;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item findItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found item" + id));
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item updateItem) {
        Item findItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found" + id));
        BeanUtils.copyProperties(updateItem, findItem, "id");
        return itemRepository.save(findItem);
    }

    public void removeItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found item" + id));
        itemRepository.delete(item);
    }
}
