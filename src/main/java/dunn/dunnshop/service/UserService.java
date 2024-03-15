package dunn.dunnshop.service;

import dunn.dunnshop.domain.User;
import dunn.dunnshop.dto.UserDto;
import dunn.dunnshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RequiredArgsConstructor //final 키워드 있어야함.
//@NoArgsConstructor
//@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository; //의존 관계 주입

    public void save(User user){
        userRepository.save(user);
    }

    public List<UserDto> findAllUser() {
        final List<User> allUser = userRepository.findAll();
        return allUser.stream().map(u -> new UserDto(u.getEmail(),u.getUserid())).toList();
    }
}
