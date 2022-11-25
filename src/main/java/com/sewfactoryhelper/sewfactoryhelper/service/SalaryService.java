package com.sewfactoryhelper.sewfactoryhelper.service;

import com.sewfactoryhelper.sewfactoryhelper.dao.SalaryRepository;
import com.sewfactoryhelper.sewfactoryhelper.dao.UserRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.entity.Users;
import com.sewfactoryhelper.sewfactoryhelper.enums.Product;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private UserRepository userRepository;


    public void save(Salary salary) {
        this.salaryRepository.save(salary);
    }

   // public Salary getSalary (Product product, Role role) {
   //     return (salaryRepository.findByProductAndRole(product, role));
   // }
//
    public Salary createSalary ( Product product, Role role) throws Exception {
        Salary salary = salaryRepository.findByProductAndRole(product, role);
        if(salary == null) {
            throw new Exception("  ");
        }
        salary.setPrice (salary.getPrice());
        return salaryRepository.save(salary);
    }

    public Salary updateSalary (Salary salary, Long userId, Product product, Role role) throws Exception {
        Users users = userRepository.findById(userId).get();
        if (users == null) {
            throw new Exception(" ");
        }
        Salary oldSalary  = salaryRepository.findByProductAndRole(product, role);
        oldSalary.setPrice(salary.getPrice());
        oldSalary.setProduct(product);
        oldSalary.setRole(role);
        return salaryRepository.save(salary);
    }

    public void deleteSalary (Long id) {
        salaryRepository.deleteById(id);
    }
}
