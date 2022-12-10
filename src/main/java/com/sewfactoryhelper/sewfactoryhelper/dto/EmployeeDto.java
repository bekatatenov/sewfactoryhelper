package com.sewfactoryhelper.sewfactoryhelper.dto;

import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private Long productId;
    private Role role;
    private Integer qty;
    private Integer priceId;
    private Integer reject;
    private Integer salary;
}
