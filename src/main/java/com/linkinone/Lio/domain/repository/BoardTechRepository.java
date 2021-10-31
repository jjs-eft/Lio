package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.BoardTechEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTechRepository extends JpaRepository<BoardTechEntity, Integer> {
}
