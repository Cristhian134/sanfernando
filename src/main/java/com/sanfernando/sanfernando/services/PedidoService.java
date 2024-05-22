package com.sanfernando.sanfernando.services;

import java.util.List;

import com.sanfernando.sanfernando.dtos.requests.PedidoFormRequest;
import com.sanfernando.sanfernando.dtos.requests.PedidoRequest;
import com.sanfernando.sanfernando.dtos.requests.PedidoTicketRequest;
import com.sanfernando.sanfernando.dtos.responses.PedidoFormResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoListaReponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoProductoResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoResponse;
import com.sanfernando.sanfernando.dtos.responses.PedidoTicketResponse;

public interface PedidoService {
  public PedidoFormResponse newForm(PedidoFormRequest pedidoFormRequest);
  public List<PedidoProductoResponse> getProductos();
  public List<PedidoListaReponse> getAll();
  public PedidoTicketResponse newTicket(PedidoTicketRequest pedidoTicketRequest);
  public PedidoResponse newPedido(PedidoRequest pedidoRequest);
}
