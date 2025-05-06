package com.kitm.ems_backend.security.services;


import com.kitm.ems_backend.entity.User;
import com.kitm.ems_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not found with username " + username));

        return UserDetailsImpl.build(user);
    }
}
