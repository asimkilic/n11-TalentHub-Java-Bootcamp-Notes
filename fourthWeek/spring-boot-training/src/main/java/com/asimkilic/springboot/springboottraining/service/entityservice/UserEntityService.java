package com.asimkilic.springboot.springboottraining.service.entityservice;

import com.asimkilic.springboot.springboottraining.dao.UserDao;
import com.asimkilic.springboot.springboottraining.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {
    @Autowired
    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;

    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
