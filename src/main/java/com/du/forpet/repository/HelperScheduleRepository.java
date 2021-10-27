package com.du.forpet.repository;

import com.du.forpet.domain.entity.HelperSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelperScheduleRepository extends JpaRepository<HelperSchedule, Long> {
    HelperSchedule findByHelper_id(Long id);
}
