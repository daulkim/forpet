package com.du.forpet.service;

import com.du.forpet.admin.service.AdminService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    @Transactional
    @Rollback(false)
    public void approve_test() {
        Long approve = adminService.approve(5L);
    }
}
