package com.sewfactoryhelper.sewfactoryhelper.entity;


import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends BaseEntity{

    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false)
    Product product;

    @Enumerated (EnumType.STRING)
    Role role;

    @Column (name = "qty", nullable = false)
    Integer qty;

    @ManyToOne
    @JoinColumn (name = "price_id", nullable = false)
    Salary price;

    @Column (name = "reject")
    Integer reject;

    @Column (name = "salary")
    Integer salary;
}
