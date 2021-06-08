package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.config.JwtTokenUtil;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoC.backenddesappapi.security.JwtRequest;
import ar.edu.unq.desapp.grupoC.backenddesappapi.security.JwtResponse;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.UserService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.wrapper.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {
        userService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetail userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getId()));
    }

    @PostMapping("/register")
    public User register(@RequestParam("user") String username, @RequestParam("password") String pws, @RequestParam("email") String email){
        return userService.addUser(username, pws, email);
    }

}
