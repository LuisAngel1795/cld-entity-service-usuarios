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
  E400("400.gs-pmo-tablero-control-catalogos.1004",
      "Solicitud mal formada, favor de validar.",
      "https://dev-api.bancoazteca.com.mx/info#400.gs-pmo-tablero-control-catalogos.1004"),

  /**
   * No autorizado.
   */
  E401("401.Gs-Pmo-Aplicaciones-Tablero-Control-Catalogos.1000",
      "No estas autorizado, favor de validar.",
      "https://dev-api.bancoazteca.com.mx/info#401.Gs-Pmo-Aplicaciones-Tablero-Control-Catalogos.100401"),

  /**
   * No encontrado.
   */
  E404("404.gs-pmo-tablero-control-catalogos.1004",
      "Información no encontrada, favor de validar.",
      "https://dev-api.bancoazteca.com.mx/info#404.gs-pmo-tablero-control-catalogos.1404"),

  /**
   * Error interno.
   */
  E500("500.gs-pmo-tablero-control-catalogos.1005",
      "Error interno del servidor.",
      "https://dev-api.bancoazteca.com.mx/info#500.gs-pmo-tablero-control-catalogos.1005");

  private String codigo;
  private String mensaje;
  private String info;


  public static ExepcionInfo findByCodigo(String codigo) {
    ECodes response = ECodes.valueOf(codigo);
    return ExepcionInfo.builder().codigo(response.codigo).info(response.info).mensaje(response.mensaje).build();
  }

}
