package com.du.forpet;

import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
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

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void addHelperScheduleScheduler() {
        List<Helper> helperList = helperRepository
                                            .findAll()
                                            .stream()
                                            .collect(Collectors.toList());

        Iterator<Helper> it = helperList.iterator();

        while(it.hasNext()){
            Helper target = it.next();
            HelperScheduleSaveRequestDto helperSchedule = HelperScheduleSaveRequestDto
                                                                .builder()
                                                                .date(LocalDate.now())
                                                                .helper(target)
                                                                .build();
            target.add(helperSchedule);
        }
    }
}
