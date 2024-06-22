package com.sanfernando.sanfernando.dao;

import java.util.List;

import com.sanfernando.sanfernando.dtos.requests.seguimiento.SeguimientoVehiculoCrearRequest;
import com.sanfernando.sanfernando.dtos.requests.seguimiento.SeguimientoVehiculoDetalleActualizarRequest;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransporstistaListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransportistaDetalleResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoDetalleResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoPedidoListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoVehiculoDetallesResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoVehiculoListaResponse;

public interface SeguimientoDao {
  public List<SeguimientoTrasladoListaResponse> getTrasladosProceso();
  public SeguimientoTrasladoDetalleResponse getTrasladoProcesoDetalle(String codGuiaRemision);
  public SeguimientoTrasladoPedidoListaResponse getTrasladoProcesoPedidos(String codGuiaRemision);
  public int actualizarTrasladoProcesoPedido(int idPedido);
  public List<SeguimientoVehiculoListaResponse> obtenerVehiculos();
  public SeguimientoVehiculoDetallesResponse obtenerVehiculoDetalle(int idVehiculo);
  public int actualizarVehiculo(SeguimientoVehiculoDetalleActualizarRequest request);
  public int crearVehiculo(SeguimientoVehiculoCrearRequest request);
  public List<SeguimientoTransporstistaListaResponse> obtenerTransportistas();
  public SeguimientoTransportistaDetalleResponse obtenerTransportistaDetalle(int idTransportista);
}
