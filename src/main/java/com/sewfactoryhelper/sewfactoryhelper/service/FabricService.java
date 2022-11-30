package com.sewfactoryhelper.sewfactoryhelper.service;

import com.sewfactoryhelper.sewfactoryhelper.dao.FabricRepository;
import com.sewfactoryhelper.sewfactoryhelper.dao.UserRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.Fabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabricService {

    @Autowired
    private FabricRepository fabricRepository;
    @Autowired
    private UserRepository userRepository;

    public void save(Fabric fabric) {
        this.fabricRepository.save(fabric);
    }

    public void deleteFabric (Long id) {
        fabricRepository.deleteById(id);
    }
}
