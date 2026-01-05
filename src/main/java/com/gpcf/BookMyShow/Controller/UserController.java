package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.dto.UserDto;
import com.gpcf.BookMyShow.model.UserModel;
import com.gpcf.BookMyShow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

@Tag(name = "User APIs")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }
}