package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.domain.entity.BoardTechEntity;
import com.linkinone.Lio.domain.entity.CommentEntity;
import com.linkinone.Lio.domain.entity.TechEntity;
import lombok.*;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardTechDto {

    private Integer postID;
    private Integer techID;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public BoardTechEntity toEntity() {
        BoardTechEntity build = BoardTechEntity.builder()
                .postID(postID)
                .techID(techID)
                .build();
        return build;

    }

    @Builder
    public BoardTechDto(Integer postID, Integer techID, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.postID = postID;
        this.techID = techID;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
