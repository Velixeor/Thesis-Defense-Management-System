package ru.tubryansk.tdms.dto;


import ru.tubryansk.tdms.entity.Role;
import ru.tubryansk.tdms.entity.User;

import java.util.List;


public record RoleDTO(String name, String authority) {

    public static RoleDTO from(Role role) {
        return new RoleDTO(role.getName(), role.getAuthority());
    }

    public static List<RoleDTO> from(User user) {
        return user.getRoles().stream().map(RoleDTO::from).toList();
    }
}
