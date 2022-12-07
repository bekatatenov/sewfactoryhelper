package com.sewfactoryhelper.sewfactoryhelper.dto;

import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private Long productId;
    private Role role;
    private String price;
}
