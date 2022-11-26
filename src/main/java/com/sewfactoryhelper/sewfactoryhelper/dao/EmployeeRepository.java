package com.sewfactoryhelper.sewfactoryhelper.dao;

import com.sewfactoryhelper.sewfactoryhelper.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
