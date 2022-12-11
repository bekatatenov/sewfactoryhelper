package com.sewfactoryhelper.sewfactoryhelper.dto;

import com.sewfactoryhelper.sewfactoryhelper.enums.FabricType;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FabricDto {
    private Long id;
    private Long productId;
    private Role role;
    private FabricType fabricType;
    private Integer fabric_web_num;
    private String fabric_web_color;
    private Integer length_web;
    private Integer waste;
    private Integer expense;
    private Integer qty;
    private Integer priceId;
    private Integer salary;
}
