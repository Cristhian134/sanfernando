package com.sanfernando.sanfernando.dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sanfernando.sanfernando.dao.ReporteDao;
import com.sanfernando.sanfernando.dtos.requests.ReporteProgramacionRequest;
import com.sanfernando.sanfernando.dtos.responses.ReporteOperacionResponse;
import com.sanfernando.sanfernando.dtos.responses.ReporteProgramacionResponse;
import com.sanfernando.sanfernando.utils.Conexion;
import com.sanfernando.sanfernando.utils.TimeUtils;

@Repository
public class ReporteDaoImpl implements ReporteDao{

  private final Conexion con = new Conexion();
  private final TimeUtils timeUtils = new TimeUtils();

  @Override
  public List<ReporteOperacionResponse> getReporteOperacion() {
    con.startConexion();
    List<ReporteOperacionResponse> reporteOperacionResponses = new ArrayList<>();
    try {
      String query = 
        "SELECT ot.cod_tipo_operacion, ot.descripcion, SUM(hora_fin-hora_inicio), COUNT(*)::integer, SUM(hora_fin-hora_inicio)/COUNT(*) as tiempo_medio, " +  
        "STRING_AGG ( " + 
          "o.id_operacion::character varying, " + 
          "'*' " + 
          ") lista_cod_operacion " + 
        "FROM operacion_tipo AS ot " + 
        "LEFT JOIN operacion AS o ON o.cod_tipo_operacion = ot.cod_tipo_operacion " + 
        "GROUP BY " + 
        "ot.cod_tipo_operacion; ";
      PreparedStatement ps = con.getCon().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ReporteOperacionResponse reporteOperacionResponse = ReporteOperacionResponse
          .builder()
          .idOperacionTipo(rs.getInt("cod_tipo_operacion"))
          .cantidad(rs.getInt("count"))
          .tiempoTotal(rs.getTime("sum"))
          .tiempoMedio(timeUtils.convertTimeToHours(rs.getTime("tiempo_medio")))
          .nombreOperacion(rs.getString("descripcion"))
          .listaOperacion(rs.getString("lista_cod_operacion"))
          .build();
          reporteOperacionResponses.add(reporteOperacionResponse);
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return reporteOperacionResponses;
  }

  @Override
  public ReporteProgramacionResponse newProgramacion(ReporteProgramacionRequest reporteProgramacionRequest) {
    con.startConexion();
    ReporteProgramacionResponse reporteProgramacionResponse = new ReporteProgramacionResponse();
    try {
      String query = 
        "INSERT INTO programacion_reporte " +
        "( cod_representante, cod_reporte_formato, cod_reporte_estado, cod_reporte_frecuencia, fecha_inicio, fecha_fin ) VALUES " +
        "(?, ?, ?, ?, ?, ?);";
      PreparedStatement ps = con.getCon().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
      System.out.println("Preparando batch: \n\n\n");
      ps.setInt(1, reporteProgramacionRequest.getIdRepresentante());
      ps.setInt(2, reporteProgramacionRequest.getIdReporteFormato());
      ps.setInt(3, reporteProgramacionRequest.getIdReporteEstado());
      ps.setInt(4, reporteProgramacionRequest.getIdReporteFrecuencia());

      java.sql.Date sqlDateInicio = java.sql.Date.valueOf(reporteProgramacionRequest.getFechaInicio());
      ps.setDate(5, sqlDateInicio);
      java.sql.Date sqlDateFin = java.sql.Date.valueOf(reporteProgramacionRequest.getFechaFin());
      ps.setDate(6, sqlDateFin);

      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      while (rs.next()) {
        reporteProgramacionResponse.setIdProgramacionReporte(rs.getInt(1));
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    con.closeConexion();
    return reporteProgramacionResponse;
  }
}
