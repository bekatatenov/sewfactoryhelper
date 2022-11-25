package com.sewfactoryhelper.sewfactoryhelper.entity;


import com.sewfactoryhelper.sewfactoryhelper.enums.Product;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "salary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Salary extends BaseEntity{
    @Column(name = "salary_one", nullable = false)
    String salary_one;

    @Enumerated (EnumType.STRING)
    Role role;

    @Enumerated (EnumType.STRING)
    Product product;
}
