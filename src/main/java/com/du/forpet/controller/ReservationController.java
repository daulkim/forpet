package com.du.forpet.controller;

import com.du.forpet.domain.ServiceType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reservations")
@Controller
public class ReservationController {

    @GetMapping("/reserve")
    public String reserve(Model model) {
        model.addAttribute("serviceType", ServiceType.values());
        return "reservation-request";
    }

    @GetMapping("/update")
    public String update() {
        return "reservation-update";
    }
}
