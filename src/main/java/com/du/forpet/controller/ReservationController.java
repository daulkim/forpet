package com.du.forpet.controller;

import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ServiceTypeResponseDto;
import com.du.forpet.service.MemberService;
import com.du.forpet.service.ReservationService;
import com.du.forpet.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final ServiceTypeService serviceTypeService;
    private final MemberService memberService;

    @GetMapping("/reserve")
    public String reserve(Model model, Principal principal) {
        List<ServiceTypeResponseDto> serviceTypeList = serviceTypeService.findAllServiceName();
        List<PetResponseDto> petList = memberService.findPetByLoginId(principal.getName());

        model.addAttribute("serviceTypeList", serviceTypeList);
        model.addAttribute("petList", petList);

        return "reservation-request";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("reservation", reservationService.findById(id));
        return "reservation";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ReservationResponseDto dto = reservationService.findById(id);
        model.addAttribute("reservation", dto);
        return "reservation-update";
    }

}
