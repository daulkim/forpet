package com.du.forpet.repository;

import com.du.forpet.domain.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findWithAuthByLoginId(String loginId);

    Optional<Member> findByLoginId(String loginId);
}
