package com.cld.usuarios.models;

public class Accesos
{
  private String idAcceso;
  private String accesoPrivado;
  private String accesoPublico;
  private String accesoSimetrico;
  private String codigoAutentificacionHash;

  public Accesos(String idAcceso, String accesoPrivado, String accesoPublico, String accesoSimetrico, String codigoAutentificacionHash) {
    this.idAcceso = idAcceso;
    this.accesoPrivado = accesoPrivado;
    this.accesoPublico = accesoPublico;
    this.accesoSimetrico = accesoSimetrico;
    this.codigoAutentificacionHash = codigoAutentificacionHash;
  }

  public Accesos() {
  }

  public String getIdAcceso() {
    return idAcceso;
  }

  public void setIdAcceso(String idAcceso) {
    this.idAcceso = idAcceso;
  }

  public String getAccesoPrivado() {
    return accesoPrivado;
  }

  public void setAccesoPrivado(String accesoPrivado) {
    this.accesoPrivado = accesoPrivado;
  }

  public String getAccesoPublico() {
    return accesoPublico;
  }

  public void setAccesoPublico(String accesoPublico) {
    this.accesoPublico = accesoPublico;
  }

  public String getAccesoSimetrico() {
    return accesoSimetrico;
  }

  public void setAccesoSimetrico(String accesoSimetrico) {
    this.accesoSimetrico = accesoSimetrico;
  }

  public String getCodigoAutentificacionHash() {
    return codigoAutentificacionHash;
  }

  public void setCodigoAutentificacionHash(String codigoAutentificacionHash) {
    this.codigoAutentificacionHash = codigoAutentificacionHash;
  }

  @Override
  public String toString() {
    return "Accesos{" +
        "idAcceso='" + idAcceso + '\'' +
        ", accesoPrivado='" + accesoPrivado + '\'' +
        ", accesoPublico='" + accesoPublico + '\'' +
        ", accesoSimetrico='" + accesoSimetrico + '\'' +
        ", codigoAutentificacionHash='" + codigoAutentificacionHash + '\'' +
        '}';
  }
}
