package dunn.dunnshop.Entity;

import dunn.dunnshop.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 모든 컬럼에 대해 getter, setter 설정함
// 생성자 세팅
@Entity
@Getter
@Setter
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 5, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String address;

    public static Users createUser(UserDto userDto) {
        Users users = new Users();


        users.setUserId(userDto.getUserId());
        users.setPassword(userDto.getPassword());
        users.setName(userDto.getName());
        users.setPhone(userDto.getPhone());
        users.setEmail(userDto.getEmail());
        users.setAddress(userDto.getAddress());

        return users;
    }
}
