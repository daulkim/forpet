package com.du.forpet;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import com.du.forpet.repository.HelperScheduleRepository;
import lombok.RequiredArgsConstructor;
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
        LocalDate date = LocalDate.now().plusWeeks(1);

        while(it.hasNext()){
            Helper target = it.next();

            boolean isNotExistNActive = isNotExistNActive(target, date);

            if(isNotExistNActive) saveSchedule(target, date);
        }
    }

    private void saveSchedule(Helper helper, LocalDate date) {
        HelperScheduleSaveRequestDto helperSchedule = HelperScheduleSaveRequestDto
                                                                    .builder()
                                                                    .date(date)
                                                                    .helper(helper)
                                                                    .isDefault("N")
                                                                    .build();
        helper.add(helperSchedule);
    }

    private boolean isNotExistNActive(Helper helper, LocalDate date) {
        boolean isExist = helperScheduleRepository
                            .findByHelper_idAndDate(helper.getId(), date)
                            .isPresent();

        boolean isActive = helper.getStatus() == ActivityStatus.ACTIVE;

        return  !isExist && isActive;
    }
}
