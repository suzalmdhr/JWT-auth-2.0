package com.jwt.Jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.Jwt.Model.User;

public interface UserDao extends JpaRepository<User,Integer>{

    User findByEmail(String email);

}
