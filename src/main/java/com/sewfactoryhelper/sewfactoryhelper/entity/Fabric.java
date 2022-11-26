package com.sewfactoryhelper.sewfactoryhelper.entity;


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

    @Column (name = "fabric_type") //Наименование типа ткани (подкладочная, отделочная, основная)
    String fabric_type;

    @Column (name = "fabric_web_num", nullable = false) //Номер рулона
    Integer fabric_web_num;

    @Column (name = "fabric_web_color", nullable = false) //Цвет рулона
    String fabric_web_color;

    @Column (name = "length_web", nullable = false)//Длина рулона
    Integer length_web;

    @Column (name = "qty", nullable = false)//Количество готовых единиц
    Integer qty;

    @Column (name = "expense", nullable = false)//Расход метров ткани на 1 единицу рулона
    Integer expense;

    @Column (name = "waste", nullable = false)//Остаток метров с одного рулона
    Integer waste;

    @ManyToOne
    @JoinColumn(name = "price_id", nullable = false)
    Salary price;

    @Column (name = "salary") //Заработная плата: прайс * количество
    Integer salary;
}
