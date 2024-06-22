package com.sanfernando.sanfernando.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanfernando.sanfernando.dtos.requests.ReporteProgramacionRequest;
import com.sanfernando.sanfernando.dtos.requests.reportes.ReporteRequest;
import com.sanfernando.sanfernando.dtos.requests.seguimiento.SeguimientoVehiculoCrearRequest;
import com.sanfernando.sanfernando.dtos.requests.seguimiento.SeguimientoVehiculoDetalleActualizarRequest;
import com.sanfernando.sanfernando.dtos.responses.reporte.ReporteProgramacionResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransporstistaListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTransportistaDetalleResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoDetalleResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoTrasladoPedidoListaResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoVehiculoDetallesResponse;
import com.sanfernando.sanfernando.dtos.responses.seguimiento.SeguimientoVehiculoListaResponse;
import com.sanfernando.sanfernando.services.SeguimientoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/seguimiento")
@RequiredArgsConstructor
public class SeguimientoController {
  
  @Autowired
  private SeguimientoService seguimientoService;

  @GetMapping(value = "trasladosProceso")
  public ResponseEntity<Object> getTrasladosProceso() {
    List<SeguimientoTrasladoListaResponse> response = seguimientoService.getTrasladosProceso();
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "trasladoProcesoDetalle")
  public ResponseEntity<Object> getTrasladosProceso(@RequestParam String codGuiaRemision) {
    SeguimientoTrasladoDetalleResponse response = seguimientoService.getTrasladoProcesoDetalle(codGuiaRemision);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "trasladoProcesoPedidos")
  public ResponseEntity<Object> getTrasladosProcesoPedidos(@RequestParam String codGuiaRemision) {
    SeguimientoTrasladoPedidoListaResponse response = seguimientoService.getTrasladoProcesoPedidos(codGuiaRemision);
    return ResponseEntity.ok(response);
  }

  @PutMapping(value = "actualizarTrasladoProcesoPedido")
  public ResponseEntity<Object> actualizarTrasladoProcesoPedido(@RequestParam int idPedido) {
    int response = seguimientoService.actualizarTrasladoProcesoPedido(idPedido);
    if (response > 0) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(value = "obtenerVehiculos")
  public ResponseEntity<Object> obtenerVehiculos() {
    List<SeguimientoVehiculoListaResponse> response = seguimientoService.obtenerVehiculos();
    return ResponseEntity.ok(response);
  } 

  @GetMapping(value = "obtenerVehiculoDetalle")
  public ResponseEntity<Object> obtenerVehiculoDetalle(@RequestParam int idVehiculo) {
    SeguimientoVehiculoDetallesResponse response = seguimientoService.obtenerVehiculoDetalle(idVehiculo);
    return ResponseEntity.ok(response);
  } 

  @PutMapping(value = "actualizarVehiculo")
  public ResponseEntity<Object> actualizarVehiculoDetalle(@RequestBody SeguimientoVehiculoDetalleActualizarRequest request) {
    int response = seguimientoService.actualizarVehiculo(request);
    if (response > 0) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping(value = "crearVehiculo")
  public ResponseEntity<Object> crearVehiculo(@RequestBody SeguimientoVehiculoCrearRequest request) {
    int response = seguimientoService.crearVehiculo(request);
    if (response > 0) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(value = "obtenerTransportistas")
  public ResponseEntity<Object> obtenerTransportistas() {
    List<SeguimientoTransporstistaListaResponse> response = seguimientoService.obtenerTransportistas();
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "obtenerTransportistaDetalle")
  public ResponseEntity<Object> obtenerTransportistaDetalle(@RequestParam int idTransportista) {
    SeguimientoTransportistaDetalleResponse response = seguimientoService.obtenerTransportistaDetalle(idTransportista);
    return ResponseEntity.ok(response);
  }

}
