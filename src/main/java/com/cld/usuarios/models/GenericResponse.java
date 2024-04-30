package com.cld.usuarios.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <b>GenericResponse.java</b>
 *
 * @version: cld-entity-service-usuarios-v1 1.0
 * @descripcion: Clase GenericResponse
 * @author: Luis Silva
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    private String mensaje;
    private String folio;
    private T resultado;

    public GenericResponse() {
    }
    public GenericResponse(String mensaje, String folio, T resultado) {
        this.mensaje = mensaje;
        this.folio = folio;
        this.resultado = resultado;
    }

    /**
     * @return String
     */
    public String getMensaje() {
        return this.mensaje;
    }

    /**
     * @param mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return String
     */
    public String getFolio() {
        return this.folio;
    }

    /**
     * @param folio
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return T
     */
    public T getResultado() {
        return this.resultado;
    }

    /**
     * @param resultado
     */
    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                " mensaje='" + getMensaje() + "'" +
                ", folio='" + getFolio() + "'" +
                ", resultado='" + getResultado() + "'" +
                "}";
    }

}
