package com.du.forpet.repository;

import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r where r.serviceType.serviceName in :serviceTypes")
    List<Reservation> findAllByServiceType(@Param("serviceTypes") List<String> servieName);
}
