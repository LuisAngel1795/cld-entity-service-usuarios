package com.cld.usuarios.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yorbin Nuñez Martinez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExepcionInfo {
  private String codigo;
  private String mensaje;
  private String info;
}
