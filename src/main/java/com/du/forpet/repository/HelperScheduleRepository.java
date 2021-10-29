package com.du.forpet.repository;

import com.du.forpet.domain.entity.HelperSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HelperScheduleRepository extends JpaRepository<HelperSchedule, Long> {
    HelperSchedule findByHelper_id(@Param(value = "helperId") Long id, @Param(value = "date") LocalDate reserveDate);
}
