package ru.tubryansk.tdms.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String login;
    private String password;
    private String fullName;
    private String mail;
    private String numberPhone;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;
    List<Integer> roleId;
}
