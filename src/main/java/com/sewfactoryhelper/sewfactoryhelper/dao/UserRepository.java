package com.sewfactoryhelper.sewfactoryhelper.dao;

import com.sewfactoryhelper.sewfactoryhelper.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findFirstByLogin (String login);
    Users findById (String id);
}
