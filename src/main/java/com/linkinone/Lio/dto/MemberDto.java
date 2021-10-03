package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private long mem_id;
    private String name;
    private String password;
    private String email;
    private String nickname;
    private String user_auth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mem_id(mem_id)
                .name(name)
                .password(password)
                .email(email)
                .nickname(nickname)
                .user_auth(user_auth)
                .build();
    }

    @Builder
    public MemberDto(long mem_id, String name, String password, String email, String nickname, String user_auth, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.mem_id = mem_id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.user_auth = user_auth;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }
}