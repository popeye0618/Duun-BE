package dunn.dunnshop.controller;

import dunn.dunnshop.Entity.Users;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/user/all")
    public List<UserDto> getUserForm() {
        return userService.getAllUsers();
    }

    @ResponseBody
    @PostMapping("/user/new")
    public UserDto userForm(@RequestBody UserDto userDto) {

        Users users = Users.createUser(userDto);
        userService.saveUser(users);

        log.info("userDto={}", userDto);
        log.info("name={}, email={}", userDto.getName(), userDto.getEmail());

        return userDto;
    }
}
