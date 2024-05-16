package com.sanfernando.sanfernando.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanfernando.sanfernando.dao.UserDao;
import com.sanfernando.sanfernando.models.User;
import com.sanfernando.sanfernando.services.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserDao userDao;

  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }

}
