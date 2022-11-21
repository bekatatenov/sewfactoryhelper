package com.sewfactoryhelper.sewfactoryhelper.Service;

import com.sewfactoryhelper.sewfactoryhelper.dao.UserRepository;
import com.sewfactoryhelper.sewfactoryhelper.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;
        import java.util.ArrayList;
        import java.util.List;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public void save(UserEntity user) {
        this.userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}
