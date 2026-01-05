package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.Repository.RoleRepository;
import com.gpcf.BookMyShow.Repository.UserRepository;
import com.gpcf.BookMyShow.dto.AuthResponseDto;
import com.gpcf.BookMyShow.dto.LoginRequestDto;
import com.gpcf.BookMyShow.dto.RegisterRequestDto;
import com.gpcf.BookMyShow.model.Role;
import com.gpcf.BookMyShow.model.UserModel;
import com.gpcf.BookMyShow.security.CustomUserDetailsService;
import com.gpcf.BookMyShow.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthResponseDto register(RegisterRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role userRole = roleRepository.findByrole("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        UserModel user = new UserModel();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(Set.of(userRole));

        userRepository.save(user);

        return new AuthResponseDto("User registered successfully", null);
    }

    public AuthResponseDto login(LoginRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(dto.getEmail());

        String token = jwtService.generateJwt(userDetails);

        return new AuthResponseDto("Login successful", token);
    }
}
