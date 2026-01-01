package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.dto.UserDto;
import com.gpcf.BookMyShow.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

@Tag(name = "User APIs")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Add User")
    public UserDto create(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User By ID")
    public UserDto getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @Operation(summary = "Get All Users")
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }
}
