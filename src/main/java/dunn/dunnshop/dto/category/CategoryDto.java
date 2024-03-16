package dunn.dunnshop.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CategoryDto {
    private Long itemId;
    private String itemName;
    private int price;
    private  String img;
}
