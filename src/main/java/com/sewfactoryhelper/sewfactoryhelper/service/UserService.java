package com.sewfactoryhelper.sewfactoryhelper.service;

import com.sewfactoryhelper.sewfactoryhelper.dao.UserRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.Fabric;
import com.sewfactoryhelper.sewfactoryhelper.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers () {
        return userRepository.findAll();
    }

    public void save(Users user) {
        this.userRepository.save(user);
    }

    public Users getUserById(long id) {
        Optional<Users> optional = userRepository.findById(id);
        Users users1 = null;
        if (optional.isPresent()) {
            users1 = optional.get();
        } else {
            throw new RuntimeException(" User not found for this id: " +id);
        }
        return users1;
    }
    public void deleteUser (long id) {
        this.userRepository.deleteById(id);
    }

    public Page<Users> findPaginatedUser(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }
}