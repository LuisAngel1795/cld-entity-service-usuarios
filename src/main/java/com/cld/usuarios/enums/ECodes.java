package com.cld.usuarios.enums;

import com.cld.usuarios.models.ExepcionInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public enum ECodes {


  /**
   * The E 400.
   */
  E400("400.cld-entity-service-usuarios.1004",
      "Solicitud mal formada, favor de validar."),

  /**
   * No autorizado.
   */
  E401("401.cld-entity-service-usuarios.1000",
      "No estas autorizado, favor de validar."),

  /**
   * No encontrado.
   */
  E404("404.cld-entity-service-usuarios.1004",
      "Información no encontrada, favor de validar."),

  /**
   * Error interno.
   */
  E500("500.cld-entity-service-usuarios.1005",
      "Error interno del servidor.");

  private String codigo;
  private String mensaje;


  public static ExepcionInfo findByCodigo(String codigo) {
    ECodes response = ECodes.valueOf(codigo);
    return ExepcionInfo.builder().codigo(response.codigo).mensaje(response.mensaje).build();
  }

}
