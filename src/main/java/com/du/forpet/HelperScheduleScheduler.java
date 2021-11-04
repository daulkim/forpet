package com.du.forpet;

import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.HelperSchedule;
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
        LocalDate date = LocalDate.now();

        while(it.hasNext()){
            Helper target = it.next();
            boolean isExist = helperScheduleRepository.findByHelper_idAndDate(target.getId(),date).isPresent();

            if(!isExist){
                HelperScheduleSaveRequestDto helperSchedule = HelperScheduleSaveRequestDto
                                                                            .builder()
                                                                            .date(date)
                                                                            .helper(target)
                                                                            .build();
                target.add(helperSchedule);
            }
        }
    }
}
