package com.sanfernando.sanfernando.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanfernando.sanfernando.dao.SeguimientoDao;
import com.sanfernando.sanfernando.dtos.requests.seguimiento.SeguimientoVehiculoCrearRequest;
import com.sanfernando.sanfernando.dtos.requests.seguimiento.SeguimientoVehiculoDetalleActualizarRequest;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransporstistaListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransportistaDetalleResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransporstistaListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoDetalleResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoPedidoListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoVehiculoDetallesResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoVehiculoListaResponse;
import com.sanfernando.sanfernando.services.SeguimientoService;

@Service
public class SeguimientoServiceImpl implements SeguimientoService{
  
  @Autowired
  private SeguimientoDao seguimientoDao;

  @Override
  public List<SeguimientoTrasladoListaResponse> getTrasladosProceso() {
    return seguimientoDao.getTrasladosProceso();
  }

  @Override
  public SeguimientoTrasladoDetalleResponse getTrasladoProcesoDetalle(String codGuiaRemision) {
    return seguimientoDao.getTrasladoProcesoDetalle(codGuiaRemision);
  }

  @Override
  public SeguimientoTrasladoPedidoListaResponse getTrasladoProcesoPedidos(String codGuiaRemision) {
    return seguimientoDao.getTrasladoProcesoPedidos(codGuiaRemision);
  }

  @Override
  public int actualizarTrasladoProcesoPedido(int idPedido) {
    return seguimientoDao.actualizarTrasladoProcesoPedido(idPedido);
  }

  @Override
  public List<SeguimientoVehiculoListaResponse> obtenerVehiculos() {
    return seguimientoDao.obtenerVehiculos();
  }

  @Override
  public SeguimientoVehiculoDetallesResponse obtenerVehiculoDetalle(int idVehiculo) {
    return seguimientoDao.obtenerVehiculoDetalle(idVehiculo);
  }

  @Override
  public int actualizarVehiculo(SeguimientoVehiculoDetalleActualizarRequest request) {
    return seguimientoDao.actualizarVehiculo(request);
  }

  @Override
  public int crearVehiculo(SeguimientoVehiculoCrearRequest request) {
    return seguimientoDao.crearVehiculo(request);
  }

  @Override
  public List<SeguimientoTransporstistaListaResponse> obtenerTransportistas() {
    return seguimientoDao.obtenerTransportistas();
  }

  @Override
  public SeguimientoTransportistaDetalleResponse obtenerTransportistaDetalle(int idTransportista) {
    return seguimientoDao.obtenerTransportistaDetalle(idTransportista);
  }
}
