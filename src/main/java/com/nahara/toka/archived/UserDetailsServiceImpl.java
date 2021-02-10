/*package com.nahara.toka.service;

import com.nahara.toka.model.User;
import com.nahara.toka.archived.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
     @Autowired
     //public UserDetailsServiceImpl(@Qualifier("user")UserRepository userRepository) {
     public UserDetailsServiceImpl() {
        //this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User userDetails = userRepository.findByUsername(userName);
        if(userDetails == null){
            throw new UsernameNotFoundException(userName);
        }
        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),userDetails.getPassword(), new ArrayList<>());
    }
}*/
