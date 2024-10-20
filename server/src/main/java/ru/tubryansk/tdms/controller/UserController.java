package ru.tubryansk.tdms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.service.AuthenticationService;
import ru.tubryansk.tdms.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public UserDTO getCurrentUser() {
        return userService.getCallerUser().map(user -> UserDTO.from(user, true)).orElse(UserDTO.unauthenticated());
    }

    @PostMapping("/logout")
    public void logout() {
        authenticationService.logout();
    }

    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) {
        authenticationService.login(username, password);
    }
}
