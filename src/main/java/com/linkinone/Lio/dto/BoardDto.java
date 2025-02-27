package com.linkinone.Lio.dto;

import com.linkinone.Lio.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long postid;
    private String boardtype;
    private String writer;
    private String title;
    private String content;
    private String tag;
    private Integer hits;
    private Integer recommend;
    private Integer NOP;
    private String tech;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;



    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .postid(postid)
                .boardtype(boardtype)
                .writer(writer)
                .title(title)
                .content(content)
                .tag(tag)
                .hits(hits)
                .recommend(recommend)
                .NOP(NOP)
                .tech(tech)
                .build();
        return build;

    }

    @Builder
    public BoardDto(Long postid, String boardtype, String title, String writer, String content, String tag, Integer hits, Integer recommend, Integer NOP, LocalDateTime createdDate, LocalDateTime modifiedDate, String tech) {
        this.postid = postid;
        this.boardtype = boardtype;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.tag = tag;
        this.hits = hits;
        this.recommend = recommend;
        this.NOP = NOP;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.tech = tech;

    }

}
