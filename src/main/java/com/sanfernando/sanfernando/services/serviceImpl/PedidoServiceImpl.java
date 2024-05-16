package com.sanfernando.sanfernando.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanfernando.sanfernando.dao.PedidoDao;
import com.sanfernando.sanfernando.dtos.requests.PedidoFormCreateDTO;
import com.sanfernando.sanfernando.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
  
  @Autowired
  private PedidoDao pedidoDao;

  @Override
  public PedidoFormCreateDTO createForm(PedidoFormCreateDTO pedidoFormCreateDTO) {
    return pedidoDao.createForm(pedidoFormCreateDTO);
  }
}
