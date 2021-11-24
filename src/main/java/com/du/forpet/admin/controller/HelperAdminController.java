package com.du.forpet.admin.controller;

import com.du.forpet.admin.service.HelperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/helper")
@RestController
public class HelperAdminController {

    private final HelperAdminService helperAdminService;

    @PutMapping("/{id}/approval/")
    public Long approve(@PathVariable Long id) {
        return helperAdminService.approve(id);
    }
}
