package com.du.forpet.repository;

import com.du.forpet.domain.entity.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {
}
