package ar.edu.unq.desapp.grupoC.backenddesappapi.services;


import ar.edu.unq.desapp.grupoC.backenddesappapi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoC.backenddesappapi.wrapper.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        ar.edu.unq.desapp.grupoC.backenddesappapi.model.User us = userRepository.findByName(username);

        List<GrantedAuthority> rols = new ArrayList<>();
        rols.add(new SimpleGrantedAuthority("ADMIN"));

        return new UserDetail(us.getName(), us.getPassword(), us.getId());
    }
    public User userById(Long id){
        return userRepository.findById(id).get();
    }

    public User addUser(String username, String pws, String email) {
        User user = new User(username, passwordEncoder.encode(pws), email);
        return userRepository.save(user);
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
