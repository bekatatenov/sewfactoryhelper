package com.sewfactoryhelper.sewfactoryhelper.entity;

import com.sewfactoryhelper.sewfactoryhelper.entity.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @Column (name = "name", unique = true)
    private String name;

    @Column (name = "color")
    private String color;




    //DRESS_DOTTED,
    //DRESS_DOTTED_WHITE,
    //DRESS_DOTTED_BLACK,
    //DRESS_DOTTED_RED,
    //COSTUME_BLAZER_AND_PANTS_BLACK,
    //COSTUME_BLAZER_AND_PANTS_GREY,
    //COSTUME_BLAZER_AND_SKIRT_BLACK,
    //COSTUME_BLAZER_AND_SKIRT_RED,
    //HOODIE_BLACK,
    //HOODIE_GREY
}
