package com.example.setup.controller;

import com.example.setup.object.AuthenticationResponse;
import com.example.setup.object.UserDetailDTO;
import com.example.setup.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody UserDetailDTO userDetailDTO) throws Exception {
        return ResponseEntity.ok(authService.signup(userDetailDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>  login(@RequestBody UserDetailDTO userDetailDTO) throws Exception {
        return ResponseEntity.ok(authService.login(userDetailDTO));
    }
}
