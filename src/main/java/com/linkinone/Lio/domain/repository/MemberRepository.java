package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String userEmail);
    String deleteByEmail(String userEmail);
}
