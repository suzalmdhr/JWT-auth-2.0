package com.jwt.Jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.Jwt.Model.User;
import com.jwt.Jwt.dao.UserDao;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
User userByEmail = userDao.findByEmail(email);
                    if(userByEmail == null){
                            throw new UsernameNotFoundException("User not found");
                    }
CustomUserDetails customUserDetails =new CustomUserDetails(userByEmail);
    
        return customUserDetails;
    }

}
