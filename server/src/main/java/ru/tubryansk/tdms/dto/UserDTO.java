package ru.tubryansk.tdms.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import ru.tubryansk.tdms.entity.User;

import java.time.ZonedDateTime;
import java.util.List;


@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public record UserDTO(
        boolean authenticated,
        String login,
        String password,
        String fullName,
        String email,
        String phone,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        List<RoleDTO> authorities) {

    public static UserDTO unauthenticated() {
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
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .authorities(RoleDTO.from(user))
                .build();
    }
}
