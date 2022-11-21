package com.sewfactoryhelper.sewfactoryhelper.dao;


import com.sewfactoryhelper.sewfactoryhelper.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByLogin(String login);
}