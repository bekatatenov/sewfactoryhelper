package com.sewfactoryhelper.sewfactoryhelper.dao;

import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.enums.Product;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository <Salary, Long> {
    Salary findByProductAndRole (Product product, Role role);
}
