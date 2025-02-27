package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContainingAndBoardtypeAndTech(String keyword, String BT, String tech);
    List<BoardEntity> findByTitleContainingAndBoardtype(String keyword, String BT);
    Page<BoardEntity> findAllByBoardtype(String BT, Pageable pageable);
    Long countByBoardtype(String BT);

    @Modifying
    @Query("update BoardEntity p set p.hits = p.hits + 1 where p.postid = :postid")
    int increaseHits(@Param("postid") Long postid);

    @Modifying
    @Query("update BoardEntity p set p.hits = p.hits - 1 where p.postid = :postid")
    int decreaseHits(@Param("postid") Long postid);

    @Modifying
    @Query("update BoardEntity p set p.recommend = p.recommend + 1 where p.postid = :postid")
    int increaseRecom(@Param("postid") Long postid);




}
