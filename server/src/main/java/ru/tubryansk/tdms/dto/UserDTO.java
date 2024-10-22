package ru.tubryansk.tdms.dto;


import lombok.Builder;
import ru.tubryansk.tdms.entity.Role;
import ru.tubryansk.tdms.entity.User;

import java.time.ZonedDateTime;
import java.util.List;


@Builder
public record UserDTO(
        boolean authenticated,
        String login,
        String password,
        String fullName,
        String email,
        String phone,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        List<String> authorities) {

    public static UserDTO fromUnauthenticated() {
        return UserDTO.builder()
                .authenticated(false)
                .build();
    }

    public static UserDTO from(User user, boolean anonymize) {
        return UserDTO.builder()
                .authenticated(true)
                .login(user.getLogin())
                .password(anonymize ? "" : user.getPassword())
                .fullName(user.getFullName())
                .email(user.getMail())
                .phone(user.getNumberPhone())
                .createdAt(user.getCreateAt())
                .updatedAt(user.getUpdateAt())
                .authorities(user.getRoles().stream().map(Role::getAuthority).toList())
                .build();
    }
    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login());
        user.setPassword(userDTO.password());
        user.setFullName(userDTO.fullName());
        user.setMail(userDTO.email());
        user.setNumberPhone(userDTO.phone());
        user.setCreateAt(userDTO.createdAt());
        user.setUpdateAt(userDTO.updatedAt());
        return user;
    }
}
