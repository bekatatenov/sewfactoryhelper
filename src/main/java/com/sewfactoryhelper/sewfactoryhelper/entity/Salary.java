package com.sewfactoryhelper.sewfactoryhelper.entity;


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
public class Salary extends BaseEntity {
    @Column(name = "price", nullable = false)
    String price;

    @Enumerated (EnumType.STRING)
    Role role;

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false)
    Product product;
}
