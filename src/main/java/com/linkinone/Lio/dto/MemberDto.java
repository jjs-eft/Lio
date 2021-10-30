package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.Role;
import com.linkinone.Lio.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private long memid;
    private String name;
    private String password;
    private String email;
    private String nickname;
    private Role userrole;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .memid(memid)
                .name(name)
                .password(password)
                .email(email)
                .nickname(nickname)
                .userrole(userrole)
                .build();
    }

    @Builder
    public MemberDto(long memid, String name, String password, String email, String nickname, Role userrole, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.memid = memid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.userrole = userrole;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }
}