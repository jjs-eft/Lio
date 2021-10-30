package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.domain.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {

    private Long commentid;
    private Long postid;
    private String writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public CommentEntity toEntity() {
        CommentEntity build = CommentEntity.builder()
                .commentid(commentid)
                .postid(postid)
                .writer(writer)
                .content(content)
                .build();
        return build;

    }

    @Builder
    public CommentDto(Long commentid, String content, Long postid, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate){
            this.commentid = commentid;
            this.postid = postid;
            this.content = content;
            this.writer = writer;
            this.createdDate = createdDate;
            this.modifiedDate = modifiedDate;
    }
}
