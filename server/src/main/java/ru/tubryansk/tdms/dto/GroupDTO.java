package ru.tubryansk.tdms.dto;

import ru.tubryansk.tdms.entity.Group;

public record GroupDTO(String name, UserDTO principalUser) {

    public static GroupDTO from(Group group) {
        return new GroupDTO(
                group.getName(),
                UserDTO.from(group.getPrincipalUser(), true)
        );
    }
}
