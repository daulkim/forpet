package com.du.forpet.repository;

import com.du.forpet.domain.entity.Helper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HelperRepository extends JpaRepository<Helper, Long> {

    boolean existsByLoginId(String loginId);

    Optional<Helper> findWithAuthByLoginId(String loginId);
}

