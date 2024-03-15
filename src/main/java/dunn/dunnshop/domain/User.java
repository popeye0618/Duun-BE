package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name="users") // Table 명은 ALL 소문자 or ALL 대문자
@Entity
@Getter    //JPA가 Getter로 명칭된것을 가져감
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본 생성자 생성 , 하지만 우리가 쓸 기본 생성자가 아니기 때문에 AccessLevel.Protected
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="id")
    private Long id;

    @Column(nullable = false, name = "user_id", length = 20, unique = true)
    private String userid;

    @Column(nullable = false, name = "password", length = 20)
    private String password;

    @Column(nullable = false, name = "user_name", length = 5)
    private String username;

    @Column(nullable = false, name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(nullable = false, name = "email", length = 50, unique=true )
    private String email;

    @Column(nullable = false, name = "address", length = 100)
    private String address;

    @Builder // 1. 우리가 쓸 생성자가 아니여서 private  2. @Setter 대신에 Builder 패턴으로
    private User(String userid, String password, String username, String phoneNumber, String email, String address) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

}
