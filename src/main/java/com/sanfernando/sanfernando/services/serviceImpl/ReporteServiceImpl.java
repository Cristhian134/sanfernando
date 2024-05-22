package com.sanfernando.sanfernando.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanfernando.sanfernando.dao.ReporteDao;
import com.sanfernando.sanfernando.dtos.requests.ReporteProgramacionRequest;
import com.sanfernando.sanfernando.dtos.responses.ReporteOperacionResponse;
import com.sanfernando.sanfernando.dtos.responses.ReporteProgramacionResponse;
import com.sanfernando.sanfernando.services.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService{

  @Autowired
  private ReporteDao reporteDao;

  @Override
  public List<ReporteOperacionResponse> getReporteOperacion() {
    return reporteDao.getReporteOperacion();
  }

  @Override
  public ReporteProgramacionResponse newProgramacion(ReporteProgramacionRequest reporteProgramacionRequest) {
    return reporteDao.newProgramacion(reporteProgramacionRequest);
  }


}
