package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPostid(Long postid);
    Void deleteByPostid(Long postid);
}
