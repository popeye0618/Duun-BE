package dunn.dunnshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatBotRequestDto {
    private final String chatting;
    private final String dump;
}
