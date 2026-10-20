package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Dto.RegisterRequestDto;
import com.example.LibraryManagement.Entity.User;
import com.example.LibraryManagement.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register-admin")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDto registerRequestDto){
        return  ResponseEntity.ok(authenticationService.registerAdminUSer(registerRequestDto));
    }
}
