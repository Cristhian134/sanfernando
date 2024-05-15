package com.sanfernando.sanfernando.services.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanfernando.sanfernando.dao.UserDao;
import com.sanfernando.sanfernando.dtos.requests.LoginRequest;
import com.sanfernando.sanfernando.dtos.responses.LoginResponse;
import com.sanfernando.sanfernando.models.User;
import com.sanfernando.sanfernando.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
  
  @Autowired
  private UserDao userDao;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {

    List<String> loginResponses = userDao.getUserByDni((String) loginRequest.getDni());

    // List<LoginResponse> loginResponses = userDao.getUserByDni((String) loginRequest.getDni());

    // System.out.println(loginRequest);
    // List<User> loginResponses = userDao.getUserByDni((String) loginRequest.getUsername());
    // System.out.println("\n\n\n\n"+loginResponses);

    // LoginResponse loginResponse = LoginResponse
    //   .builder()
    //   .dni(loginResponses.get(0).getDni())
    //   .build();

    LoginResponse loginResponse = LoginResponse
      .builder()
      .dni(loginResponses.get(0))
      .build();

    // LoginResponse loginResponse = LoginResponse
    //   .builder()
    //   .username(loginResponses.get(0).getDni())
    //   .password(loginResponses.get(0).getDni())
    //   .build();
    
    return loginResponse;
  }
}
