package com.sewfactoryhelper.sewfactoryhelper.entity;

import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users extends BaseEntity{

    @Column (name = "fio", nullable = false)
    String fio;

    @Column(name = "login",nullable = false,unique = true)
    String login;

    @Column(name = "password",nullable = false, unique = true)
    String password;

    @Enumerated(EnumType.STRING)
    Role roles;

    @Column (name = "phone_number", nullable = false, unique = true)
    String phone_number;

    @Column(name = "is_active")
    Boolean isActive;
}
