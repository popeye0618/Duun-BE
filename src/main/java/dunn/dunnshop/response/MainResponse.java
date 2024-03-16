package dunn.dunnshop.response;

import dunn.dunnshop.dto.main.MainDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class MainResponse {
    private List<MainDto> items;
}
