package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long board_id;
    private Long post_id;
    private String writer;
    private String title;
    private String content;
    private String tag;
    private Integer hits;
    private Integer recommend;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;



    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .post_id(post_id)
                .board_id(board_id)
                .writer(writer)
                .title(title)
                .content(content)
                .tag(tag)
                .hits(hits)
                .recommend(recommend)
                .build();
        return build;

    }

    @Builder
    public BoardDto(Long post_id, Long board_id, String title, String writer, String content, String tag, Integer hits, Integer recommend, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.post_id = post_id;
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.tag = tag;
        this.hits = hits;
        this.recommend = recommend;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

    }

}
