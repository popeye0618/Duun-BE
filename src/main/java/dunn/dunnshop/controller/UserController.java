package dunn.dunnshop.controller;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.response.MyPageDto;
import dunn.dunnshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @GetMapping("/check-id")
    public ResponseEntity<?> checkUserId(@RequestParam String id) {
        boolean available = userService.findUserId(id);

        if (available) {
            return ResponseEntity.badRequest().body("ID 중복 존재");
        } else {
            return ResponseEntity.ok().body("ID is available");
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password) {
        boolean success = userService.login(userId, password);
        if (success) {
            return "Login Success";
        } else {
            return "Login Failed";
        }
    }
}
