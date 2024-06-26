package com.example.setup.service;

import com.example.setup.config.JwtService;
import com.example.setup.object.AuthenticationResponse;
import com.example.setup.object.UserDetail;
import com.example.setup.object.UserDetailDTO;
import com.example.setup.respository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse signup(UserDetailDTO userDetailDTO) throws Exception{
        var username = userDetailDTO.getUsername();

        if (userDetailRepository.getByUsername(username).isPresent())
            throw new Exception("Username already exists");

        UserDetail entity = new UserDetail();
        entity.setUsername(userDetailDTO.getUsername());
        entity.setPassword(passwordEncoder.encode(userDetailDTO.getPassword()));
        entity.setRole("user");

        userDetailRepository.save(entity);

        var token = jwtService.generateToken(entity);

        var response = new AuthenticationResponse();
        response.setToken(token);
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setPassword(entity.getPassword());
        response.setRole(entity.getRole());
        return response;
    }

    public AuthenticationResponse login(UserDetailDTO userDetailDTO) throws Exception{
        var username = userDetailDTO.getUsername();
        var password = userDetailDTO.getPassword();

        var auth = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(auth);

        var entity = userDetailRepository.getByUsername(username);

        if(entity.isPresent()){
            var token = jwtService.generateToken(entity.get());
            var entity1 = entity.get();
            var response = new AuthenticationResponse();
            response.setToken(token);
            response.setId(entity1.getId());
            response.setUsername(entity1.getUsername());
            response.setPassword(entity1.getPassword());
            response.setRole(entity1.getRole());
            return response;
        }
        return null;
    }
}
