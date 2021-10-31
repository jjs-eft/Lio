package com.linkinone.Lio.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="mapping_board")
public class BoardTechEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int postID;

    @Column(length = 10)
    private int techID;


    @Builder
    public BoardTechEntity(Integer postID, Integer techID) {
        this.postID = postID;
        this.techID = techID;
    }

}