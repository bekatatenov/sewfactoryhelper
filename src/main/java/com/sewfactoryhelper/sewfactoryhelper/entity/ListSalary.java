package com.sewfactoryhelper.sewfactoryhelper.entity;


import com.sewfactoryhelper.sewfactoryhelper.enums.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "list_salary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListSalary extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    Users users;

    @Enumerated (EnumType.STRING)
    Product product;

    @ManyToOne
    @JoinColumn (name = "salary_id", nullable = false)
    Salary salary;

    @ManyToOne
    @JoinColumn (name = "qty_id", nullable = false)
    Fabric fabric_qty;

    @ManyToOne
    @JoinColumn (name = "price_id", nullable = false)
    Fabric fabric_price;

    @Column (name = "all_salary", nullable = false)
    Integer all_salary;
}
