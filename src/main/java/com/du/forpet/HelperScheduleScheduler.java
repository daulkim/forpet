package com.du.forpet;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.HelperSchedule;
import com.du.forpet.repository.HelperRepository;
import com.du.forpet.repository.HelperScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HelperScheduleScheduler {

    private final HelperRepository helperRepository;
    private final HelperScheduleRepository helperScheduleRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void addHelperScheduleScheduler() {
        List<Helper> helperList = helperRepository
                                            .findAll()
                                            .stream()
                                            .collect(Collectors.toList());

        Iterator<Helper> it = helperList.iterator();
        LocalDate date = LocalDate.now();

        while(it.hasNext()){
            Helper target = it.next();

            boolean isExist = isExist(target, date);
            boolean isNotExistNActive = !isExist && isActive(target);

            if(isNotExistNActive) saveSchedule(target, date);
            if(isExist) deleteSchedule(target, date.minusDays(1));
        }
    }

    private void deleteSchedule(Helper helper, LocalDate date) {
        helperScheduleRepository.deleteByHelper_idAndDate(helper.getId(), date);
    }

    private void saveSchedule(Helper helper, LocalDate date) {
        HelperSchedule defaultSchedule  = getDefaultSchedule(helper.getId());
        HelperScheduleSaveRequestDto helperSchedule = HelperScheduleSaveRequestDto
                                                                    .builder()
                                                                    .date(date)
                                                                    .helper(helper)
                                                                    .isDefault("N")
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
    }


    private boolean isActive(Helper helper) {
        boolean isActive = helper.getStatus() == ActivityStatus.ACTIVE;
        return  isActive;
    }

    private boolean isExist(Helper helper, LocalDate date) {
        boolean isExist = helperScheduleRepository
                            .findByHelper_idAndDate(helper.getId(), date)
                            .isPresent();
        return isExist;
    }

    private HelperSchedule getDefaultSchedule(Long helperId) {
        return helperScheduleRepository
                .findByHelper_idAndIsDefault(helperId, "Y")
                .orElseThrow(() -> new IllegalIdentifierException("해당 헬퍼의 Default Schedule 이 존재하지 않습니다."));
    }
}
