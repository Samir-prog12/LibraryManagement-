package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Dto.LoginRequestDto;
import com.example.LibraryManagement.Dto.LoginResponseDto;
import com.example.LibraryManagement.Dto.RegisterRequestDto;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDto request){
        return  ResponseEntity.ok(authenticationService.registerNormalUser(request));

    }
@PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return  ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }
}
