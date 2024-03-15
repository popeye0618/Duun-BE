package dunn.dunnshop.dto;

import lombok.Getter;

@Getter
public class ItemDto {
    private Long id;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private String itemCategory;

    public ItemDto(Long id, String itemName, Long itemPrice, String itemDescription, String itemCategory) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }
}
