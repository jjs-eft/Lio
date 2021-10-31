package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.domain.entity.CommentEntity;
import com.linkinone.Lio.domain.entity.TechEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TechDto {

    private Integer techID;
    private String techName;



    public TechEntity toEntity() {
        TechEntity build = TechEntity.builder()
                .techID(techID)
                .techName(techName)
                .build();
        return build;

    }

    @Builder
    public TechDto(Integer techID, String techName){
        this.techID = techID;
        this.techName = techName;
    }
}
