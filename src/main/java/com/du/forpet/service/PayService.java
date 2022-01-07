package com.du.forpet.service;

import com.du.forpet.domain.PayStatus;
import com.du.forpet.domain.dto.PayResponseDto;
import com.du.forpet.domain.dto.PaySaveRequestDto;
import com.du.forpet.domain.entity.Pay;
import com.du.forpet.domain.entity.PayHistory;
import com.du.forpet.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;

    public Long save(PaySaveRequestDto payDto) {
        Pay pay = payRepository.save(payDto.toEntity());
        pay.addHistory(PayHistory.builder()
                                .status(PayStatus.REQUEST)
                                .payType("C")
                                .build());
        return pay.getId();
    }

    public PayResponseDto findById(Long id) {
        Pay entity = payRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 결제 건이 존재 하지 않습니다. id: " + id));
        return new PayResponseDto(entity);
    }

    public Long cancel(Long id) {
        Pay pay = payRepository
                    .findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 결제 건이 존재 하지 않습니다. id: " + id));
        pay.update();
        return id;
    }
}
