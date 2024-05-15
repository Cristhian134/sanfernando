package com.sanfernando.sanfernando.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.dtos.responses.LoginResponse;
import com.sanfernando.sanfernando.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

  @Query(value = "SELECT dni FROM persona WHERE dni = ?1", nativeQuery = true)
  List<String> getUserByDni(String dni);

  @Query(value = "SELECT * FROM persona", nativeQuery = true)  
  List<User> getAllUsers();
}
