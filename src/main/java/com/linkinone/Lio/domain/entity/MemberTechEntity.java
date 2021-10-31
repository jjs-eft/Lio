package com.linkinone.Lio.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(MemberTechPK.class)
@Table(name ="mapping_user")
public class MemberTechEntity implements Serializable {

    @Id
    @Column(length = 20)
    private String userID;

    @Id
    @Column(length = 10)
    private int techID;

    @Column
    private LocalDateTime createdDate;


    @Builder
    public MemberTechEntity(Long ID, String userID, Integer techID, LocalDateTime createdDate) {
        this.userID = userID;
        this.techID = techID;
        this.createdDate = createdDate;
    }

}