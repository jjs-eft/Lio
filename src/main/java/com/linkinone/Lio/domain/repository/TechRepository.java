package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.TechEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepository extends JpaRepository<TechEntity, Long> {
}
