package dunn.dunnshop.service;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found user" + id));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updateUser) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found" + id));
        BeanUtils.copyProperties(updateUser, findUser, "id");
        return userRepository.save(findUser);
    }

    public void removeUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found" + id));
        userRepository.delete(user);
    }

    public boolean findUserId(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        return userOptional.isPresent();
    }

    public boolean login(String userId, String password) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
