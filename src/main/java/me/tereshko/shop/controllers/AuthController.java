package me.tereshko.shop.controllers;

import lombok.RequiredArgsConstructor;
import me.tereshko.shop.beans.JwtTokenUtil;
import me.tereshko.shop.dto.UserDto;
import me.tereshko.shop.dto.jwt.JWTRequest;
import me.tereshko.shop.dto.jwt.JWTResponse;
import me.tereshko.shop.exceptions_handling.MarketError;
import me.tereshko.shop.exceptions_handling.ResourceNotFoundException;
import me.tereshko.shop.models.User;
import me.tereshko.shop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JWTRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        User user = userService.findByUsername(authRequest.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User with username: " + authRequest.getUsername() + " doesn't found"));

        String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> response = new HashMap<>();
        response.put("token", new JWTResponse(token));
        response.put("user", new UserDto(user));

        return ResponseEntity.ok(response);
    }
}
