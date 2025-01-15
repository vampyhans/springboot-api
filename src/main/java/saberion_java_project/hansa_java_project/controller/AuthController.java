package saberion_java_project.hansa_java_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import saberion_java_project.hansa_java_project.dto.UserLoginDto;
import saberion_java_project.hansa_java_project.dto.UserRegistrationDto;
import saberion_java_project.hansa_java_project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegistrationDto userDto) {
        System.out.println("Register API hit with data: " + userDto);
        try {
            String response = userService.registerUser(userDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto loginDto) {
        try {
            String token = userService.loginUser(loginDto);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authenticated User Roles: " + auth.getAuthorities());

            return ResponseEntity.ok("Bearer " + token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
