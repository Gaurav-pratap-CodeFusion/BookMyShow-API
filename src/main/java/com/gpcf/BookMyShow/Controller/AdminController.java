package com.gpcf.BookMyShow.Controller;


import org.springframework.web.bind.annotation.RestController;


import com.gpcf.BookMyShow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/make-admin/{userId}")
    public String makeAdmin(@PathVariable Long userId) {

        userService.assignAdminRole(userId);
        return "User promoted to ADMIN successfully";
    }
}
