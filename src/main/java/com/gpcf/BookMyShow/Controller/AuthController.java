package com.gpcf.BookMyShow.Controller;

import com.gpcf.BookMyShow.dto.AuthResponseDto;
import com.gpcf.BookMyShow.dto.LoginRequestDto;
import com.gpcf.BookMyShow.dto.RegisterRequestDto;
import com.gpcf.BookMyShow.model.UserModel;
import com.gpcf.BookMyShow.service.AuthService;
import com.gpcf.BookMyShow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody @Valid RegisterRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authService.login(request));
    }
}
