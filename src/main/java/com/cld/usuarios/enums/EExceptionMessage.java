package com.cld.usuarios.enums;


import com.cld.usuarios.models.ExepcionInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public enum EExceptionMessage {
  E401, E401B, E400, E404, E500;
  private static final Logger LOGGER = LoggerFactory.getLogger(EExceptionMessage.class);


  public ExepcionInfo getInfoExepcion() {

    return ECodes.findByCodigo(this.name());
  }


}

