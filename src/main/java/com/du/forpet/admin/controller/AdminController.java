package com.du.forpet.admin.controller;

import com.du.forpet.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/helper/{id}/approval/")
    public Long approve(@PathVariable Long id) {
        return adminService.approve(id);
    }
}
