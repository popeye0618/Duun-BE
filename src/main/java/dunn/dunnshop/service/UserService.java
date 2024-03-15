package dunn.dunnshop.service;

import dunn.dunnshop.Entity.Users;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users saveUser(Users users) {
        return userRepository.save(users);
    }

    public List<UserDto> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        List<UserDto> userAllList = new ArrayList<>();

        for (Users user : usersList) {
            UserDto userDto = new UserDto();

            userDto.setUserId(user.getUserId());
            userDto.setPassword(user.getPassword());
            userDto.setName(user.getName());
            userDto.setPhone(user.getPhone());
            userDto.setEmail(user.getEmail());
            userDto.setAddress(user.getAddress());

            userAllList.add(userDto);
        }
        return userAllList;
    }
}
