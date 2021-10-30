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
    private Long commentid;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private Long postid;

    @Column(length = 50, nullable = false)
    private String writer;

    @Builder
    public CommentEntity(Long commentid, String content, Long postid, String writer){
        this.commentid = commentid;
        this.postid = postid;
        this.content = content;
        this.writer = writer;
    }
}
