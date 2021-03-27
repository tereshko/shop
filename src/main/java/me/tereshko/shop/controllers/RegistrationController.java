package me.tereshko.shop.controllers;

import lombok.RequiredArgsConstructor;
import me.tereshko.shop.configs.SecurityConfig;
import me.tereshko.shop.dto.UserDto;
import me.tereshko.shop.models.Role;
import me.tereshko.shop.models.User;
import me.tereshko.shop.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final UserService userService;
    private final SecurityConfig securityConfig;


    @PostMapping
    public UserDto userRegistration(@RequestBody User user) {
        Role role = new Role();
        role.setId(1L);

        user.setRoles(Collections.singleton(role));
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        userService.saveUser(user);
        UserDto userDto = new UserDto(user);

        return userDto;
    }
}
