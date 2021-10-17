package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
