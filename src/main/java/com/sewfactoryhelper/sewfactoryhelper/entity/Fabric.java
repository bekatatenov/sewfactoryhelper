package com.sewfactoryhelper.sewfactoryhelper.entity;


import com.sewfactoryhelper.sewfactoryhelper.enums.FabricType;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "fabric")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fabric extends BaseEntity{

    @Enumerated (EnumType.STRING)
    Role role;

    @Enumerated (EnumType.STRING)
    FabricType fabricType;

    @Column (name = "fabric_web_num") //Номер рулона
    Integer fabric_web_num;

    @Column (name = "fabric_web_color") //Цвет рулона
    String fabric_web_color;

    @Column (name = "length_web")//Длина рулона
    Integer length_web;

    @Column (name = "expense")//Расход метров ткани на 1 единицу рулона
    Integer expense;

    @Column (name = "waste")//Остаток метров с одного рулона
    Integer waste;

    @Column (name = "qty")//Количество готовых единиц
    Integer qty;

    @ManyToOne
    @JoinColumn(name = "price_id")
    Salary price;

    @ManyToOne
    @JoinColumn (name = "product_id")
    Product product;

    @Column (name = "salary") //Заработная плата: прайс * количество
    Integer salary;
}
