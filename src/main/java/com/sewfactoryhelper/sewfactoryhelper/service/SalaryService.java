package com.sewfactoryhelper.sewfactoryhelper.service;

import com.sewfactoryhelper.sewfactoryhelper.dao.SalaryRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    public void save(Salary salary) {
        this.salaryRepository.save(salary);
    }

    public List<Salary> getAllSalary () {
        return salaryRepository.findAll();
    }

    public Salary getSalaryById(long id) {
        return salaryRepository.getOne(id);
    }

    public void deleteSalary (Long id) {
        salaryRepository.deleteById(id);
    }

    public Page<Salary> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.salaryRepository.findAll(pageable);
    }

    public Salary findByProductAndRole(Long productId, Role role) {
        return this.salaryRepository.findFirstByProduct_IdAndRole(productId, role);
    }

    public Salary findById(Long SalaryID) {
        return salaryRepository.findById(SalaryID)
                .orElseThrow(()->new NoSuchElementException("Not Found"));
    }
}
