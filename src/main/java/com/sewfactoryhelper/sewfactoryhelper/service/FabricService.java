package com.sewfactoryhelper.sewfactoryhelper.service;

import com.sewfactoryhelper.sewfactoryhelper.dao.EmployeeRepository;
import com.sewfactoryhelper.sewfactoryhelper.dao.FabricRepository;
import com.sewfactoryhelper.sewfactoryhelper.dao.UserRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.Employee;
import com.sewfactoryhelper.sewfactoryhelper.entity.Fabric;
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
public class FabricService {
    @Autowired
    private FabricRepository fabricRepository;

    public void save(Fabric fabric) {
        this.fabricRepository.save(fabric);
    }

    public List<Fabric> getAllFabric () {
        return fabricRepository.findAll();
    }

    public Fabric getFabricById(long id) {
        Optional<Fabric> optional = fabricRepository.findById(id);
        Fabric fabric = null;
        if (optional.isPresent()) {
            fabric = optional.get();
        } else {
            throw new RuntimeException(" Fabric not found for this id: " +id);
        }
        return fabric;
    }

    public void deleteFabric (Long id) {
        fabricRepository.deleteById(id);
    }

    public Page<Fabric> findPaginatedFabric (int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.fabricRepository.findAll(pageable);
    }

    public List<Fabric> findAll() {
        return fabricRepository.findAll();
    }

    public Fabric findById(Long FabricID) {
        return fabricRepository.findById(FabricID)
                .orElseThrow(()->new NoSuchElementException("Not Found fabric by id"));
    }
}
