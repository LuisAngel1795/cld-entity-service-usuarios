package com.cld.usuarios.models;

import lombok.Data;
import lombok.ToString;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ToString
@Data
public class ApiError implements Serializable {

  private static final long serialVersionUID = -8923825880525299860L;

  private String codigo;
  private String mensaje;
  private String folio;
  private String info;
  private List<String> detalles;

  public ApiError() {
    super();
    this.folio = MDC.get("currentFolio").replace("-", "");
  }

  public ApiError(String codigo, String mensaje, String info, List<String> detalles) {
    super();
    this.codigo = codigo;
    this.mensaje = mensaje;
    this.folio = MDC.get("currentFolio");
    this.info = info;
    this.detalles = Collections.unmodifiableList(detalles);
  }

}
