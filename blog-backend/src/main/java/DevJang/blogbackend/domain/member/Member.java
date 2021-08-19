package DevJang.blogbackend.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "MEMBER_NAME")
    private String nickName;

    public Member() {
    }

    public Member(String password) {
        this.password = password;
    }

    public Member(String password, String nickName) {
        this.password = password;
        this.nickName = nickName;
    }

    public Member(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }
}
