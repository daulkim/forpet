package com.du.forpet.controller;

import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reserve")
    public String reserve(Model model) {
        model.addAttribute("serviceType", ServiceType.values());
        return "reservation-request";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ReservationResponseDto dto = reservationService.findById(id);
        model.addAttribute("reservation", dto);
        return "reservation-update";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("reservation", reservationService.findById(id));
        return "reservation";
    }
}
