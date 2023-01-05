package com.example.FilRougeFrontOffice.service;


import com.example.FilRougeFrontOffice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserDetails user = userRepository
                .findByuserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userEmail + " not found"));
        return user;
    }

//    public UserDetails loadUserByName(String name) throws UsernameNotFoundException {
//        UserDetails user = userRepository
//                .findByUserName(name)
//                .orElseThrow(() -> new UsernameNotFoundException("IdUser: " + id + " not found"));
//        return user;
//    }

}
