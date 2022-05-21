package com.du.forpet.repository;

import com.du.forpet.domain.entity.Helper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelperRepository extends JpaRepository<Helper, Long> {

    boolean existsByLoginId(String loginId);

}

