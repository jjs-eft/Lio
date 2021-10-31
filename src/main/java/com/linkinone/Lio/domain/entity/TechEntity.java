package com.linkinone.Lio.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="tech")
public class TechEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int techID;

    @Column(length = 20, name = "techname")
    private String techName;



    @Builder
    public TechEntity(Integer techID, String techName) {
        this.techID = techID;
        this.techName = techName;

    }

}
