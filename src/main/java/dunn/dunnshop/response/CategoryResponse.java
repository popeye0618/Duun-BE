package dunn.dunnshop.response;

import dunn.dunnshop.dto.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class CategoryResponse {
    private List<CategoryDto> items;
}
