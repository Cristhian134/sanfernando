package com.sanfernando.sanfernando.services;

import java.util.List;

import com.sanfernando.sanfernando.dtos.requests.ReporteProgramacionRequest;
import com.sanfernando.sanfernando.dtos.responses.ReporteOperacionResponse;
import com.sanfernando.sanfernando.dtos.responses.ReporteProgramacionResponse;

public interface ReporteService {
  public List<ReporteOperacionResponse> getReporteOperacion();
  public ReporteProgramacionResponse newProgramacion(ReporteProgramacionRequest reporteProgramacionRequest);
}
