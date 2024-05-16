package com.sanfernando.sanfernando.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.dao.UserDao;
import com.sanfernando.sanfernando.dtos.requests.LoginRequest;
import com.sanfernando.sanfernando.dtos.responses.LoginResponse;
import com.sanfernando.sanfernando.models.User;
import com.sanfernando.sanfernando.utils.Conexion;

@Repository
public class UserDaoImpl implements UserDao{

  private final Conexion con = new Conexion();

  @Override
  public List<User> getAll() {
    con.startConexion();
    List<User> users = new ArrayList<>();
    try {
      String query = "SELECT * FROM persona;";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        User user = User
          .builder()
          .cod_persona(rs.getInt("cod_persona"))
          .cod_estado_civil(rs.getInt("cod_estado_civil"))
          .cod_nacionalidad(rs.getInt("cod_nacionalidad"))
          .build();
        users.add(user);
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return users;
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    con.startConexion();
    List<LoginResponse> loginResponses = new ArrayList<>();
    try {
      String query = 
      "SELECT u.dni, c.nombre area, ct.tipo_cliente, representante " +
      "FROM ( " +
        "SELECT p.dni,e.cod_cliente, true AS representante FROM empleado AS e INNER JOIN persona AS p ON p.cod_persona = e.cod_persona " +
        "UNION " +
        "SELECT p.dni,r.cod_cliente, false AS representante FROM representante AS r INNER JOIN persona AS p ON p.cod_persona = r.cod_persona " +
      ") AS u " +
      "INNER JOIN cliente AS c ON c.cod_cliente = u.cod_cliente " +
      "INNER JOIN cliente_tipo AS ct ON ct.cod_cliente_tipo = c.cod_cliente_tipo " +
      "WHERE u.dni = ?; ";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      ps.setString(1,loginRequest.getDni());
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        LoginResponse loginResponse = LoginResponse
          .builder()
          .dni(rs.getString("dni"))
          .area(rs.getString("area"))
          .cliente(rs.getString("tipo_cliente"))
          .representante(rs.getBoolean("representante"))
          .build();
        loginResponses.add(loginResponse);
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return loginResponses.get(0);
  }
  
}
