package com.linkinone.Lio.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 100)
    private String tag;

    @Column(length = 10)
    @ColumnDefault("0")
    private Integer hits;

    @Column(length = 10)
    @ColumnDefault("0")
    private Integer recommend;


    @Builder
    public BoardEntity(Long post_id, Long board_id, String writer, String title, String content, String tag, Integer hits, Integer recommend) {
        this.post_id = post_id;
        this.board_id = board_id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.hits = hits;
        this.recommend = recommend;

    }



}
