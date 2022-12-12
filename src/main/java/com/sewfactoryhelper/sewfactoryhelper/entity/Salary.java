package com.sewfactoryhelper.sewfactoryhelper.entity;


import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "salary")
@Getter
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salary extends BaseEntity {
    @Column(name = "price", nullable = false)
    Integer price;

    @Enumerated (EnumType.STRING)
    Role role;

    @ManyToOne
    @JoinColumn (name = "product", nullable = false)
    Product product;


 //   public Salary(Role role, Integer price, Product byId) {
 //       this.role = role;
 //       this.price = price;
 //       this.product = byId;
 //   }
}
