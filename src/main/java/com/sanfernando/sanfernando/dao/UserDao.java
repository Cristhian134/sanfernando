package com.sanfernando.sanfernando.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

  @Query(value = "SELECT * FROM persona", nativeQuery = true)  
  List<User> getAllUsers();
}
