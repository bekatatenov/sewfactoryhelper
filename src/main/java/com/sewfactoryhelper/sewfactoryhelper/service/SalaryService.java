package com.sewfactoryhelper.sewfactoryhelper.service;

import com.sewfactoryhelper.sewfactoryhelper.dao.SalaryRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    public void save (Salary salary)  {
        this.salaryRepository.save(salary);
    }
}
