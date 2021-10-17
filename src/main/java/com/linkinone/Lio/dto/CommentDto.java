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

    private Long comment_id;
    private BoardEntity post_id;
    private String writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public CommentEntity toEntity() {
        CommentEntity build = CommentEntity.builder()
                .comment_id(comment_id)
                .post_id(post_id)
                .writer(writer)
                .content(content)
                .build();
        return build;

    }

    @Builder
    public CommentDto(Long comment_id, String content, BoardEntity post_id, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate){
            this.comment_id = comment_id;
            this.post_id = post_id;
            this.content = content;
            this.writer = writer;
            this.createdDate = createdDate;
            this.modifiedDate = modifiedDate;
    }
}
