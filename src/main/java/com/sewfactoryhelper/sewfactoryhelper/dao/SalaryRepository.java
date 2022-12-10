package com.sewfactoryhelper.sewfactoryhelper.dao;

import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.entity.Product;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository <Salary, Long> {
    Salary findFirstByProduct_IdAndRole(Long productId, Role role);
}
