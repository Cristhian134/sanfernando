package com.sanfernando.sanfernando.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.dao.PedidoDao;
import com.sanfernando.sanfernando.dtos.requests.PedidoFormRequest;
import com.sanfernando.sanfernando.dtos.requests.PedidoRequest;
import com.sanfernando.sanfernando.dtos.requests.PedidoTicketProductoRequest;
import com.sanfernando.sanfernando.dtos.requests.PedidoTicketRequest;
import com.sanfernando.sanfernando.dtos.responses.PedidoClienteResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoFormResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoListaReponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoProductoResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoTicketResponse;
import com.sanfernando.sanfernando.dtos.responses.PersonaResponse;
import com.sanfernando.sanfernando.dtos.responses.RepresentanteResponse;
import com.sanfernando.sanfernando.services.UserService;
import com.sanfernando.sanfernando.utils.Conexion;

@Repository
public class PedidoDaoImpl implements PedidoDao{

  @Autowired
  private UserService userService;

  private final Conexion con = new Conexion();

  @Override
  public List<PedidoProductoResponse> getProductos() {
    con.startConexion();
    List<PedidoProductoResponse> pedidoProductoResponses = new ArrayList<>();
    try {
      String query = 
        "SELECT ec.id_elemento_catalogo, ec.nombre, ec.descripcion,ect.descripcion tipo,ec.id_elemento_catalogo_tipo, ec.peso_unitario, ecu.descripcion unidad " +
        "FROM elemento_catalogo AS ec " +
        "LEFT JOIN elemento_catalogo_tipo AS ect ON ect.id_elemento_catalogo_tipo = ec.id_elemento_catalogo_tipo " +
        "INNER JOIN elemento_catalogo_unidad AS ecu ON ecu.cod_unidad = ec.cod_unidad; ";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        PedidoProductoResponse pedidoProductoResponse = PedidoProductoResponse
          .builder()
          .idElementoCatalogo(rs.getInt("id_elemento_catalogo"))
          .nombre(rs.getString("nombre"))
          .descripcion(rs.getString("descripcion"))
          .tipo(rs.getString("tipo"))
          .idElementoCatalogoTipo(rs.getInt("id_elemento_catalogo_tipo"))
          .peso(rs.getDouble("peso_unitario"))
          .unidad(rs.getString("unidad"))
          .build();
          pedidoProductoResponses.add(pedidoProductoResponse);
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return pedidoProductoResponses;
  }

  @Override
  public List<PedidoListaReponse> getAll() {
    con.startConexion();
    List<PedidoListaReponse> pedidoListaReponses = new ArrayList<>();
    try {
      String query = 
        "SELECT pd.cod_pedido, c.nombre, pr.prenombre, pr.primer_apellido, pr.segundo_apellido , pd.fecha_registro, pde.estado_pedido FROM pedido As pd " + 
        "INNER JOIN pedido_estado AS pde ON pde.cod_pedido_estado = pd.cod_pedido_estado " +
        "INNER JOIN representante AS r ON r.cod_representante = pd.cod_representante " +
        "INNER JOIN empleado AS e ON e.cod_empleado = pd.cod_empleado " +
        "INNER JOIN persona AS pr ON pr.cod_persona = e.cod_persona " +
        "INNER JOIN cliente AS c ON c.cod_cliente = r.cod_cliente; ";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        PedidoListaReponse pedidoListaReponse = PedidoListaReponse
          .builder()
          .idPedido(rs.getInt("cod_pedido"))
          .nombre(rs.getString("nombre"))
          .empleado(rs.getString("prenombre") + " " 
                  + rs.getString("primer_apellido") + " " 
                  + rs.getString("segundo_apellido")
              )
          .fecha(rs.getString("fecha_registro"))
          .estado(rs.getString("estado_pedido"))
          .build();
          pedidoListaReponses.add(pedidoListaReponse);
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return pedidoListaReponses;
  }

  @Override
  public PedidoFormResponse newForm(PedidoFormRequest pedidoFormRequest) {
    System.out.println("personaRequest: " + pedidoFormRequest.getPersonaRequest() + "\n\n\n");
    PersonaResponse personaResponse = userService.newPersona(pedidoFormRequest.getPersonaRequest());
    PedidoClienteResponse clienteResponse = userService.newCliente(pedidoFormRequest.getClienteRequest());
    pedidoFormRequest.getRepresentanteRequest().setIdPersona(personaResponse.getIdPersona());
    pedidoFormRequest.getRepresentanteRequest().setIdCliente(clienteResponse.getIdCliente());
    System.out.println("representanteRequest: " + pedidoFormRequest.getRepresentanteRequest() + "\n\n\n");
    RepresentanteResponse representanteResponse = userService.newRepresentante(pedidoFormRequest.getRepresentanteRequest());
    PedidoFormResponse pedidoFormResponse = PedidoFormResponse
      .builder()
      .idRepresentante(representanteResponse.getIdRepresentante())
      .build();
    return pedidoFormResponse;
  }

  @Override
  public PedidoTicketResponse newTicket(PedidoTicketRequest pedidoTicketRequest) {
    con.startConexion();
    PedidoTicketResponse pedidoTicketResponse = new PedidoTicketResponse();
    try {
      String query = 
        "INSERT INTO ticket" +
        "( fecha_entrega ) VALUES " +
        "(?); ";
      PreparedStatement ps = con.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

      java.sql.Date sqlDate = java.sql.Date.valueOf(pedidoTicketRequest.getFechaEntrega());
      ps.setDate(1, sqlDate);

      int rows =ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();
      while (rs.next()) {
        pedidoTicketResponse.setIdTicket(rs.getInt(1));
      }
    
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return pedidoTicketResponse;
  }

  public void newTicketProducto(PedidoTicketProductoRequest[] pedidoTicketProductoRequests, Integer idTicket) {
    con.startConexion();
    try {
      String query = 
        "INSERT INTO detalle_ticket_producto " +
        "( cod_ticket, id_elemento_catalogo, cantidad ) VALUES " +
        "(?, ?, ?);";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      for (PedidoTicketProductoRequest pedidoTicketProductoRequest : pedidoTicketProductoRequests) {
        ps.setInt(1, idTicket);
        ps.setInt(2, pedidoTicketProductoRequest.getIdElementoCatalogo());
        ps.setInt(3, pedidoTicketProductoRequest.getCantidad());
        ps.addBatch();
      }
      System.out.println("Preparando batch: \n\n\n");
      ps.clearParameters();
      
      ps.executeBatch();
      System.out.println("Batch realizado: \n\n\n");
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
  }

  @Override
  public PedidoResponse newPedido(PedidoRequest pedidoRequest) {
    PedidoResponse pedidoResponse = new PedidoResponse();
    PedidoTicketRequest pedidoTicketRequest = PedidoTicketRequest
      .builder()
      .fechaEntrega(pedidoRequest.getFechaEntrega())
      .build();
    PedidoTicketResponse pedidoTicketResponse = this.newTicket(pedidoTicketRequest);
    pedidoRequest.setIdTicket(pedidoTicketResponse.getIdTicket());
    this.newTicketProducto(pedidoRequest.getPedidoTicketProductoRequest(), pedidoTicketResponse.getIdTicket());
    con.startConexion();
    try {
      String query = 
        "INSERT INTO pedido" +
        "( cod_representante, cod_empleado, cod_pedido_tipo, cod_pedido_estado, cod_ticket, fecha_registro) VALUES " +
        "(?, ?, ?, ?, ?, ?); ";
      PreparedStatement ps = con.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, pedidoRequest.getIdRepresentante());
      ps.setInt(2, pedidoRequest.getIdEmpleadoRegistro());
      ps.setString(3, pedidoRequest.getIdTipoPedido());
      ps.setString(4, pedidoRequest.getIdEstadoPedido());
      ps.setInt(5, pedidoRequest.getIdTicket());

      java.sql.Date sqlDate = java.sql.Date.valueOf(pedidoRequest.getFechaRegistro());
      ps.setDate(6, sqlDate);

      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      while (rs.next()) {
        pedidoResponse.setIdPedido(rs.getInt(1));
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return pedidoResponse;
  }
  
}
