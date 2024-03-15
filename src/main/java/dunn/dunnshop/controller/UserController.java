package dunn.dunnshop.controller;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PostMapping("/user/save")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "remove successfully";
    }
}
