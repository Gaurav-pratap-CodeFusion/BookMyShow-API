package com.gpcf.BookMyShow.security;

import com.gpcf.BookMyShow.Repository.UserRepository;
import com.gpcf.BookMyShow.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new
                SimpleGrantedAuthority("ROLE_" + role.getRole())).toList();

        UserDetails build = User
                .withUsername(user.getName())
                .password(user.getPassword())
                .authorities(authorities)
                .build();

        return build;
    }
}