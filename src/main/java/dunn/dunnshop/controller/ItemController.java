package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.dto.main.MainDto;
import dunn.dunnshop.response.MainResponse;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item findItem(@PathVariable Long id) {
        return itemService.findItem(id);
    }

    @PostMapping("save")
    public Item addItem(@RequestBody Item item) {
        item.setCreatedAt(LocalDateTime.now());
        return itemService.addItem(item);
    }

    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setCreatedAt(LocalDateTime.now());
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable Long id) {
        itemService.removeItem(id);
        return "remove Successfully";
    }

    /**
     * api 명세
     */
    //좋아요 순으로 상위 4개 아이템
    @GetMapping("/home")
    public MainResponse getHome() {
        List<Item> allItems = itemService.getAllItems();

        List<Item> topLikedItems = allItems.stream().sorted((item1, item2) -> Integer
                        .compare(item2.getItemDescription().getLikes(), item1.getItemDescription().getLikes()))
                .limit(4).collect(Collectors.toList());


        MainResponse response = new MainResponse(topLikedItems.stream()
                .map(item -> new MainDto(item.getId(), item.getImg())).collect(Collectors.toList()));

        return response;
    }

    //최신순 상위 4개 아이템 (상품 업데이트하면 등록 시간 갱신됨)
    @GetMapping("/new")
    public MainResponse getNew() {
        List<Item> allItems = itemService.getAllItems();

        List<Item> topNewItems = allItems.stream()
                .sorted(Comparator.comparing(Item::getCreatedAt).reversed())
                .limit(4).collect(Collectors.toList());


        MainResponse response = new MainResponse(topNewItems.stream()
                .map(item -> new MainDto(item.getId(), item.getImg())).collect(Collectors.toList()));

        return response;
    }

    //코트 좋아요 순
    @GetMapping("/coat")
    public MainResponse getTopCoats() {
        List<Item> coats = itemService.getAllItems().stream()
                .filter(item -> "coat".equals(item.getItemCategory())).collect(Collectors.toList());

        List<Item> topCoats = coats.stream().sorted((item1, item2) -> Integer
                        .compare(item2.getItemDescription().getLikes(), item1.getItemDescription().getLikes()))
                .limit(4).collect(Collectors.toList());

        MainResponse response = new MainResponse(topCoats.stream()
                .map(item -> new MainDto(item.getId(), item.getImg())).collect(Collectors.toList()));

        return response;
    }

    @GetMapping("/pants")
    public MainResponse getTopPants() {
        List<Item> pants = itemService.getAllItems().stream()
                .filter(item -> "pants".equals(item.getItemCategory())).collect(Collectors.toList());

        List<Item> topPants = pants.stream().sorted((item1, item2) -> Integer
                        .compare(item2.getItemDescription().getLikes(), item1.getItemDescription().getLikes()))
                .limit(4).collect(Collectors.toList());

        MainResponse response = new MainResponse(topPants.stream()
                .map(item -> new MainDto(item.getId(), item.getImg())).collect(Collectors.toList()));

        return response;
    }

    @GetMapping("/sweat")
    public MainResponse getTopSweats() {
        List<Item> sweats = itemService.getAllItems().stream()
                .filter(item -> "sweat".equals(item.getItemCategory())).collect(Collectors.toList());

        List<Item> topSweats = sweats.stream().sorted((item1, item2) -> Integer
                        .compare(item2.getItemDescription().getLikes(), item1.getItemDescription().getLikes()))
                .limit(4).collect(Collectors.toList());

        MainResponse response = new MainResponse(topSweats.stream()
                .map(item -> new MainDto(item.getId(), item.getImg())).collect(Collectors.toList()));

        return response;
    }

}
