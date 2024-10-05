package com.bangbang.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String nickname;
    private String password;
    private String profileImageUrl;
    @OneToMany(mappedBy = "member")
    private List<MemberCourse> memberCourses;

    private Member(String account, String email, String nickname, String password) {
        this.account = account;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static Member create(String account, String email, String nickname, String password) {
        return new Member(account, email, nickname, password);
    }

    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
