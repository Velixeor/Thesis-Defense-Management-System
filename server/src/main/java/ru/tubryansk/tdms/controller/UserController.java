package ru.tubryansk.tdms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.service.UserService;

@RestController
@Validated
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public UserDTO getCurrentUser() {
        User principal = userService.getCallerPrincipal();
        return principal != null ? UserDTO.from(principal, true) : UserDTO.fromUnauthenticated();
    }

    @PostMapping("/logout")
    public void logout() {
        userService.logout();
    }
}
