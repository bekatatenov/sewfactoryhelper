package com.sewfactoryhelper.sewfactoryhelper.entity;


import com.sewfactoryhelper.sewfactoryhelper.enums.Product;
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
    Product product;

    @Column (name = "qty", nullable = false)
    Integer qty;

    //@ManyToMany
    //@JoinColumn (name = "price_id", nullable = false)
    //Salary salary;

    @Column (name = "reject")
    Integer reject;

    @Column (name = "salary")
    Integer salary
}
