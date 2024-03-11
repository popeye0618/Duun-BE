package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.domain.User;
import dunn.dunnshop.repository.ItemRepository;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShopController {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @PostMapping("users/save")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PostMapping("items/save")
    public void addItem(@RequestBody Item item) {
        itemRepository.save(item);
    }
}
