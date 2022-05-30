package com.du.forpet.controller;

import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ServiceTypeResponseDto;
import com.du.forpet.domain.entity.ServiceType;
import com.du.forpet.security.HelperUserDetails;
import com.du.forpet.service.MemberService;
import com.du.forpet.service.ReservationService;
import com.du.forpet.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReservationService reservationService;
    private final ServiceTypeService serviceTypeService;
    private final MemberService memberService;

    @GetMapping("/reservations/reserve")
    public String reserve(Model model, Principal principal) {
        List<ServiceTypeResponseDto> serviceTypeList = serviceTypeService.findAllServiceName();
        List<PetResponseDto> petList = memberService.findPetByLoginId(principal.getName());

        model.addAttribute("serviceTypeList", serviceTypeList);
        model.addAttribute("petList", petList);

        return "reservation/request";
    }

    @GetMapping("/reservations/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("reservation", reservationService.findById(id));
        return "reservation/reservation";
    }

    @GetMapping("/reservations/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ReservationResponseDto dto = reservationService.findById(id);
        model.addAttribute("reservation", dto);
        return "reservation/update";
    }

    @GetMapping("/helpers/reservations/request-list")
    public String reservationReqListByServiceType(@AuthenticationPrincipal HelperUserDetails user,
                                                    Model model) {
        List<String> serviceTypes = user.getServiceTypes()
                                        .stream()
                                        .map(ServiceType::getServiceName)
                                        .collect(Collectors.toList());

        model.addAttribute("list", reservationService.findAllByServiceType(serviceTypes));
        return "reservation/request-list";
    }
}
