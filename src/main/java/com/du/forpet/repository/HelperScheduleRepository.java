package com.du.forpet.repository;

import com.du.forpet.domain.entity.HelperSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HelperScheduleRepository extends JpaRepository<HelperSchedule, Long> {

    Optional<HelperSchedule> findTopByHelper_idOrderByDateDesc(Long id);

}
