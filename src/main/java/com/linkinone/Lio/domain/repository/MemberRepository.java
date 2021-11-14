package com.linkinone.Lio.domain.repository;

import com.linkinone.Lio.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String userEmail);
    String deleteByEmail(String userEmail);

    @Query("SELECT m.memid From MemberEntity m WHERE m.email = :email")
    Long findMemidByEmail(@Param("email") String email);

    @Modifying
    @Query("update MemberEntity m set m.password = :pw where m.memid = :memid")
    Integer updatepassword(@Param("memid") Long memid, @Param("pw") String pw);

}
