package ar.edu.unq.desapp.grupoC.backenddesappapi.controller;

import ar.edu.unq.desapp.grupoC.backenddesappapi.config.JwtTokenUtil;
import ar.edu.unq.desapp.grupoC.backenddesappapi.dto.UserMetrics;
import ar.edu.unq.desapp.grupoC.backenddesappapi.dto.UserRegisterDto;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Title;
import ar.edu.unq.desapp.grupoC.backenddesappapi.security.JwtRequest;
import ar.edu.unq.desapp.grupoC.backenddesappapi.security.JwtResponse;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.ReviewService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.TitleService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.services.UserService;
import ar.edu.unq.desapp.grupoC.backenddesappapi.wrapper.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {
        userService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetail userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getId(), userDetails.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody @Valid UserRegisterDto userRegisterDto){
        UserDetail user = userService.addUser(userRegisterDto.getUsername(), userRegisterDto.getPassword(), userRegisterDto.getMail());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token, user.getId(), user.getUsername()));
    }

    @GetMapping("/data")
    public ResponseEntity<UserMetrics> myData() {
        UserDetail userDetails = (UserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<Title> titlesReviewed = titleService.getTitlesReviewedBy(userDetails.getId());
        List<Review> reviewsWritten = reviewService.getReviewsByUser(userDetails.getId());

        return ResponseEntity.ok(new UserMetrics(titlesReviewed, reviewsWritten));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
