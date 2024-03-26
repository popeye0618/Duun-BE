package dunn.dunnshop.controller;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.response.MyPageDto;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PostMapping("/save")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "remove successfully";
    }

    @GetMapping("/mypage/{id}")
    public MyPageDto editProfile(@PathVariable Long id) {
        User user = userService.findUser(id);
        return new MyPageDto(user.getUserId(), user.getUsername(), user.getPhone(), user.getAddress(), user.getEmail());
    }
}
