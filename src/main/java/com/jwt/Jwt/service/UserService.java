package com.jwt.Jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.Jwt.Model.User;
import com.jwt.Jwt.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User createUser(User user){

User savedUser = userDao.save(user);
return savedUser;
    }

}
