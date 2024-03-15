package dunn.dunnshop.service;

import dunn.dunnshop.domain.Items;
import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemsRepository itemsRepository;

    public void save(Items items){
        itemsRepository.save(items);
    }

    public List<ItemDto> findAllItems(){
        final List<Items> allItems = itemsRepository.findAll();
        return allItems.stream().map(i -> new ItemDto(i.getId(),i.getItemName(),i.getItemPrice(),
                                                        i.getItemDescription(),i.getItemCategory())).toList();
    }

}
