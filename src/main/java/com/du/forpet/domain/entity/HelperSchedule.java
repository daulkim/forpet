package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class HelperSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HELPERSCHEDULE_ID")
    private Long id;

    private LocalDate date;

    private String t0900;

    private String t1000;

    private String t1100;

    private String t1200;

    private String t1300;

    private String t1400;

    private String t1500;

    private String t1600;

    private String t1700;

    private String t1800;

    private String t1900;

    private String t2000;

    @ManyToOne
    @JoinColumn(name = "HELPER_ID")
    private Helper helper;


    @Builder
    public HelperSchedule(LocalDate date, String t0900, String t1000, String t1100,
                          String t1200, String t1300, String t1400,
                          String t1500, String t1600, String t1700,
                          String t1800, String t1900, String t2000,
                          Helper helper) {
        this.date = date;
        this.t0900 = t0900;
        this.t1000 = t1000;
        this.t1100 = t1100;
        this.t1200 = t1200;
        this.t1300 = t1300;
        this.t1400 = t1400;
        this.t1500 = t1500;
        this.t1600 = t1600;
        this.t1700 = t1700;
        this.t1800 = t1800;
        this.t1900 = t1900;
        this.t2000 = t2000;
        this.helper = helper;
    }

    public boolean checkTimeAvailability(LocalDateTime startTime, LocalDateTime endTime) {

        dateValidation(startTime.toLocalDate());

        int startHour = startTime.getHour();
        int endHour = endTime.getHour();

        for (int i=0; i < (endHour - startHour); i++) {

            String keyFromStartHour =(0<startHour && startHour<10)? "t0"+startHour+"00":"t"+startHour+"00";
            String checkYN = getTimeYNMap().get(keyFromStartHour);

            if(checkYN.equals("N"))
                return false;

            startHour++;
        }

        return true;
    }

    public void dateValidation(LocalDate requestDate) {
        if(LocalDate.now().isAfter(requestDate)){
            throw new RuntimeException("예약일이 오늘보다 전일 수 없습니다.");
        }
        if(LocalDate.now().plusWeeks(1).isAfter(requestDate)){
            throw new RuntimeException("예약은 오늘로부터 1주일 후까지만 가능합니다.");
        }
    }

    public Map<String, String> getTimeYNMap(){
        Map<String, String> yNMap = new HashMap<>();

        yNMap.put("t0900",this.t0900);
        yNMap.put("t1000",this.t1000);
        yNMap.put("t1100",this.t1100);
        yNMap.put("t1200",this.t1200);
        yNMap.put("t1300",this.t1300);
        yNMap.put("t1400",this.t1400);
        yNMap.put("t1500",this.t1500);
        yNMap.put("t1600",this.t1600);
        yNMap.put("t1700",this.t1700);
        yNMap.put("t1800",this.t1800);
        yNMap.put("t1900",this.t1900);
        yNMap.put("t2000",this.t2000);

        return yNMap;
    }
}