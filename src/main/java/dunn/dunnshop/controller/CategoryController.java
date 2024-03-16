package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.dto.category.CategoryDto;
import dunn.dunnshop.response.CategoryResponse;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final ItemService itemService;

    @GetMapping("/t-shirt")
    public CategoryResponse getAllTShirts() {
        List<Item> tShirts = itemService.getAllItems().stream()
                .filter(item -> "t-shirt".equals(item.getItemCategory())).collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse(tShirts.stream()
                .map(item -> new CategoryDto(item.getId(), item.getItemName(), item.getItemPrice(), item.getImg()))
                .collect(Collectors.toList()));

        return response;
    }

    @GetMapping("/sweat")
    public CategoryResponse getAllSweat() {
        List<Item> sweats = itemService.getAllItems().stream()
                .filter(item -> "sweat".equals(item.getItemCategory())).collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse(sweats.stream()
                .map(item -> new CategoryDto(item.getId(), item.getItemName(), item.getItemPrice(), item.getImg()))
                .collect(Collectors.toList()));

        return response;
    }

    @GetMapping("/coat")
    public CategoryResponse getAllCoats() {
        List<Item> coats = itemService.getAllItems().stream()
                .filter(item -> "coat".equals(item.getItemCategory())).collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse(coats.stream()
                .map(item -> new CategoryDto(item.getId(), item.getItemName(), item.getItemPrice(), item.getImg()))
                .collect(Collectors.toList()));

        return response;
    }

    @GetMapping("/jean")
    public CategoryResponse getAllJeans() {
        List<Item> jeans = itemService.getAllItems().stream()
                .filter(item -> "jean".equals(item.getItemCategory())).collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse(jeans.stream()
                .map(item -> new CategoryDto(item.getId(), item.getItemName(), item.getItemPrice(), item.getImg()))
                .collect(Collectors.toList()));

        return response;
    }

    @GetMapping("/padding")
    public CategoryResponse getAllPadding() {
        List<Item> paddings = itemService.getAllItems().stream()
                .filter(item -> "padding".equals(item.getItemCategory())).collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse(paddings.stream()
                .map(item -> new CategoryDto(item.getId(), item.getItemName(), item.getItemPrice(), item.getImg()))
                .collect(Collectors.toList()));

        return response;
    }

}
