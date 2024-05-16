package com.sanfernando.sanfernando.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.dao.PedidoDao;
import com.sanfernando.sanfernando.dtos.requests.PedidoFormCreateDTO;
import com.sanfernando.sanfernando.utils.Conexion;

@Repository
public class PedidoDaoImpl implements PedidoDao{

  private final Conexion con = new Conexion();

  @Override
  public PedidoFormCreateDTO createForm(PedidoFormCreateDTO pedidoFormCreateDTO) {
    con.startConexion();
    try {
      String query = "INSERT INTO persona VALUES (?, ?, ?, ?,  ?, ?, ?, ?, ?);";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      ps.setInt(1,28);
      ps.setInt(2, 1);
      ps.setInt(3, 1);
      ps.setInt(4, 1);
      ps.setString(5, pedidoFormCreateDTO.getDni());
      ps.setString(6, pedidoFormCreateDTO.getApellidoPaterno());
      ps.setString(7, pedidoFormCreateDTO.getApellidoMaterno());
      ps.setString(8, pedidoFormCreateDTO.getNombre());
      ps.setString(9, "Direccion");
      ps.executeQuery();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return pedidoFormCreateDTO;
  }

}
