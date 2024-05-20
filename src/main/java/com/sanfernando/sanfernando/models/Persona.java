package com.sanfernando.sanfernando.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
  private Integer idPersona;
  private Integer idEstadoCivil;
  private Integer idNacionalidad;
  private Integer idGenero;
  private String dni;
  private String primerApellido;
  private String segundoApellido;
  private String prenombre;
  private String direccion;
}