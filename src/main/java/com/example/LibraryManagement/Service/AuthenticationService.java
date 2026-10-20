package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Dto.LoginRequestDto;
import com.example.LibraryManagement.Dto.LoginResponseDto;
import com.example.LibraryManagement.Dto.RegisterRequestDto;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Jwt.JWTService;
import com.example.LibraryManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JWTService jwtService;


    public User registerNormalUser(RegisterRequestDto request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("User Already Registered");

        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user  = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       user.setRoles(roles);

       return  userRepository.save(user);
    }

    public User registerAdminUSer(RegisterRequestDto registerRequestDto) {
        if(userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()){
            throw new RuntimeException(" AdminUser Already Register");


        }
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");
        User user  = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRoles(roles);

        return  userRepository.save(user);

    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
                        loginRequestDto.getPassword())
        );
        User user =  userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found"));

        String token  = jwtService.generateToken(user);
        return LoginResponseDto.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}
