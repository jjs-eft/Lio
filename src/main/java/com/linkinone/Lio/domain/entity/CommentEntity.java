package com.linkinone.Lio.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="Comment")
public class CommentEntity extends TimeEntity  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long comment_id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private BoardEntity post_id;

    @Column(length = 50, nullable = false)
    private String writer;

    @Builder
    public CommentEntity(Long comment_id, String content, BoardEntity post_id, String writer){
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.content = content;
        this.writer = writer;
    }
}
