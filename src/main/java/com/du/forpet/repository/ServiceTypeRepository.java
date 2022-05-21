package com.du.forpet.repository;

import com.du.forpet.domain.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, String> {
}
