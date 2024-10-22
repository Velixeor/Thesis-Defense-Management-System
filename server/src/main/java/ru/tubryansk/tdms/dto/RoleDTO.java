package ru.tubryansk.tdms.dto;


import lombok.Builder;
import ru.tubryansk.tdms.entity.Role;

@Builder
public record RoleDTO(
        Integer id,
        String name,String authority) {


    public static RoleDTO fromEntity(Role role) {
        return new RoleDTO(role.getId(), role.getName(),role.getAuthority());
    }
}
