package com.sanfernando.sanfernando.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanfernando.sanfernando.dtos.requests.PedidoFormCreateDTO;
import com.sanfernando.sanfernando.services.PedidoService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @PostMapping(value = "form")
  public ResponseEntity<Object> createForm(@RequestBody PedidoFormCreateDTO request) {
    PedidoFormCreateDTO response = pedidoService.createForm(request);
    return ResponseEntity.ok(response);
  }
}
