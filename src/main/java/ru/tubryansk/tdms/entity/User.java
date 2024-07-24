package ru.tubryansk.tdms.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "vkr")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;
    @Column(name = "number_phone", nullable = false, unique = true)
    private String numberPhone;
    @Column(name = "create_at", nullable = false)
    private ZonedDateTime createAt;
    @Column(name = "update_at")
    private ZonedDateTime updateAt;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",schema = "vkr",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> userLoyaltyLevels;

}
