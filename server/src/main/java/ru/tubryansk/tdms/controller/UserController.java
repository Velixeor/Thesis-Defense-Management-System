package ru.tubryansk.tdms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tubryansk.tdms.dto.UserDTO;
import ru.tubryansk.tdms.entity.User;
import ru.tubryansk.tdms.service.UserService;


@RestController
@Validated
@RequestMapping("/api/v1/user")
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

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

}
