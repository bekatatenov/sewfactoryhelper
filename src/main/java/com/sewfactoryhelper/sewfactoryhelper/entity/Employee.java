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

    @Enumerated (EnumType.STRING)
    Role role;

    @Column (name = "qty", nullable = false)
    Integer qty;

    @ManyToOne
    @JoinColumn (name = "price_id")
    Salary price;

    @Column (name = "reject")
    Integer reject;

    @Column (name = "salary")
    Integer salary;

    public Employee(Role role, Integer qty, Integer reject, Salary price) {
        this.role = role;
        this.qty = qty;
        this.reject = reject;
        this.price = price;
    }
}
