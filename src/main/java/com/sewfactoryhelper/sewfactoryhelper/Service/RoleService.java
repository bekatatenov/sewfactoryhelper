package com.sewfactoryhelper.sewfactoryhelper.Service;


import com.sewfactoryhelper.sewfactoryhelper.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoleByName(String name) {
        return this.roleRepository.findAllByRole(name);
    }
}
