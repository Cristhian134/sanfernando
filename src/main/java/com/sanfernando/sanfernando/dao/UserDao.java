package com.sanfernando.sanfernando.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.dtos.requests.LoginRequest;
import com.sanfernando.sanfernando.dtos.responses.LoginResponse;
import com.sanfernando.sanfernando.models.User;

@Repository
public interface UserDao {
  public List<User> getAll();
  public LoginResponse login(LoginRequest loginRequest);
}
