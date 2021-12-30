package com.asimkilic.springboot.springboottraining.dao;

import com.asimkilic.springboot.springboottraining.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
