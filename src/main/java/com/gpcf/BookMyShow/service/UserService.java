package com.gpcf.BookMyShow.service;

import com.gpcf.BookMyShow.dto.UserDto;
import com.gpcf.BookMyShow.Exception.ResourceNotFoundException;
import com.gpcf.BookMyShow.model.User;
import com.gpcf.BookMyShow.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto createUser(UserDto dto) {
        User user = mapToEntity(dto);
        return mapToDto(userRepository.save(user));
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    private User mapToEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword("encrypted"); // JWT later
        return user;
    }
}
