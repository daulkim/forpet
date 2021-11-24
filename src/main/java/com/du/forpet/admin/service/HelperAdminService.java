package com.du.forpet.admin.service;

import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HelperAdminService {

    private final HelperRepository helperRepository;

    public Long approve(Long id) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(()
                                    -> new IllegalIdentifierException("해당 헬퍼는 존재하지 않습니다. id: "+id));
        helper.approve();
        return id;
    }
}
