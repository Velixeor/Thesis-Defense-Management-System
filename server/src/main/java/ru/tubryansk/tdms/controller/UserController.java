package ru.tubryansk.tdms.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.service.UserService;


@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public UserDTO getCurrentUser() {
        return UserDTO.from(userService.getCallerPrincipal(), true);
    }

    @PostMapping("/logout")
    public void logout() {
        userService.logout();
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public void login(
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            HttpServletRequest request, HttpServletResponse response) {
        userService.loginUser(login, password, request,response);
    }
}
