package com.linkinone.Lio.domain.entity;

import com.linkinone.Lio.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long mem_id;

    @Column(length = 50, nullable = true)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 50, nullable = true)
    private String nickname;

    @Column(name = "userrole")
    @Enumerated(EnumType.STRING)
    private Role userrole = Role.MEMBER;






    @Builder
    public MemberEntity(long mem_id, String name, String password, String email, String nickname, Role userrole) {
        this.mem_id = mem_id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.userrole = userrole;

    }
}