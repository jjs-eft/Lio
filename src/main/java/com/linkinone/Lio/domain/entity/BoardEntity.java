package com.linkinone.Lio.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="board")
public class BoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long post_id;

    @Column(length = 10, nullable = true)
    private Long board_id;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 100, nullable = true)
    private String tag;

    @Builder
    public BoardEntity(Long post_id, Long board_id, String writer, String title, String content, String tag) {
        this.post_id = post_id;
        this.board_id = board_id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.tag = tag;

    }



}
