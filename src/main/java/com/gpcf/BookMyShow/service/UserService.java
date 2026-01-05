package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.Repository.RoleRepository;
import com.gpcf.BookMyShow.dto.UserDto;
import com.gpcf.BookMyShow.Exception.ResourceNotFoundException;
import com.gpcf.BookMyShow.model.Role;
import com.gpcf.BookMyShow.model.UserModel;
import com.gpcf.BookMyShow.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;

    public UserDto getUserById(Long id) {

        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToUserDto(user);
    }

    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToUserDto).toList();
    }

    private UserDto mapToUserDto(UserModel user) {

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    public void assignAdminRole(Long userId) {

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role adminRole = roleRepository.findByrole("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

        user.getRoles().add(adminRole);

        userRepository.save(user);
    }
}
