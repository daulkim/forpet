package com.du.forpet.admin.service;

import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.HelperSchedule;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final HelperRepository helperRepository;

    public Long approve(Long id) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(()
                                    -> new IllegalIdentifierException("해당 헬퍼는 존재하지 않습니다. id: "+id));
        helper.approve();
        HelperSchedule defaultSchedule = helper.getHelperSchedules().stream().filter(schedule -> schedule.getIsDefault().equals("Y")).collect(Collectors.toList()).get(0);
        LocalDate date = LocalDate.now();
        HelperScheduleSaveRequestDto helperSchedule = HelperScheduleSaveRequestDto
                                                            .builder()
                                                            .date(date)
                                                            .helper(helper)
                                                            .t0900(defaultSchedule.getT0900())
                                                            .t1000(defaultSchedule.getT1000())
                                                            .t1100(defaultSchedule.getT1100())
                                                            .t1200(defaultSchedule.getT1200())
                                                            .t1300(defaultSchedule.getT1300())
                                                            .t1400(defaultSchedule.getT1400())
                                                            .t1500(defaultSchedule.getT1500())
                                                            .t1600(defaultSchedule.getT1600())
                                                            .t1700(defaultSchedule.getT1700())
                                                            .t1800(defaultSchedule.getT1800())
                                                            .t1900(defaultSchedule.getT1900())
                                                            .t2000(defaultSchedule.getT2000())
                                                            .build();
        helper.add(helperSchedule, date, "N");
        return id;
    }
}
