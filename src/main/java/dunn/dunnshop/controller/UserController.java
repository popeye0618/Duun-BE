package dunn.dunnshop.controller;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  // @Controller + @ResponseBody
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }
    @GetMapping("/users")
    public List<UserDto> findAllUser(){
        return userService.findAllUser();
    }
}
