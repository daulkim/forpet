package com.du.forpet.service;

import com.du.forpet.repository.HelperScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HelperScheduleService {

    private final HelperScheduleRepository helperScheduleRepository;

}
