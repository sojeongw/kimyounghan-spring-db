package hello.kimyounghanjdbc.propagation;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    // jpa 스펙 상 기본 생성자가 필요하다.
    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
