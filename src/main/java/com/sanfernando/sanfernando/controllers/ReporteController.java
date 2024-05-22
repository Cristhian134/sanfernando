package com.sanfernando.sanfernando.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanfernando.sanfernando.dtos.requests.ReporteProgramacionRequest;
import com.sanfernando.sanfernando.dtos.responses.ReporteOperacionResponse;
import com.sanfernando.sanfernando.dtos.responses.ReporteProgramacionResponse;
import com.sanfernando.sanfernando.services.ReporteService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {
  
  @Autowired
  private ReporteService reporteService;

  @GetMapping(value = "operacion")
  public ResponseEntity<Object> getReporteOperacion() {
    List<ReporteOperacionResponse> response = reporteService.getReporteOperacion();
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "programacion")
  public ResponseEntity<Object> newForm(@RequestBody ReporteProgramacionRequest request) {
    ReporteProgramacionResponse response = reporteService.newProgramacion(request);
    return ResponseEntity.ok(response);
  }
}
