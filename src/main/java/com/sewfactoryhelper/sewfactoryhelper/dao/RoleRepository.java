package com.sewfactoryhelper.sewfactoryhelper.dao;


import com.sewfactoryhelper.sewfactoryhelper.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findAllByRole(String name);
}
