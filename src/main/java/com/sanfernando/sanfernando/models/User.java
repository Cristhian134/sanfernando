package com.sanfernando.sanfernando.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
  @Id
  private Integer cod_persona;
  private Integer cod_estado_civil;
  private Integer cod_nacionalidad;
  private Integer cod_genero;
  private String dni;
  private String primer_apellido;
  private String segundo_apellido;
  private String prenombre;
  private String direccion;
}