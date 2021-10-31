package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.*;
import lombok.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberTechDto {

    private String userID;
    private Integer techID;
    private LocalDateTime createdDate;


    public MemberTechEntity toEntity() {
        MemberTechEntity build = MemberTechEntity.builder()
                .userID(userID)
                .techID(techID)
                .createdDate(createdDate)
                .build();
        return build;

    }

    @Builder
    public MemberTechDto(String userID, Integer techID, LocalDateTime createdDate){
        this.techID = techID;
        this.userID = userID;
        this.createdDate = createdDate;
    }
}
