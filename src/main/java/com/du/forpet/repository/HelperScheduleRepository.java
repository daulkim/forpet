package com.du.forpet.repository;

import com.du.forpet.domain.entity.HelperSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.Optional;

public interface HelperScheduleRepository extends JpaRepository<HelperSchedule, Long> {

    Optional<HelperSchedule> findByHelper_idAndIsDefault(@Param(value = "helperId") Long id, @Param(value = "isDefault") String isDefault);

    Optional<HelperSchedule> findTopByHelper_idOrderByDateDesc(Long id);

    void deleteByHelper_idAndDate(@Param(value="helperId") Long id, @Param(value = "date") LocalDate date);
}
