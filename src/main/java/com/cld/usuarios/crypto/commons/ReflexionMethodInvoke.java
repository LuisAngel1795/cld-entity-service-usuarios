package com.cld.usuarios.crypto.commons;

import java.lang.reflect.InvocationTargetException;

/**
 * ReflexionMethodInvoke
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: ReflexionMethodInvoke
 * @param <T>
 */
public interface ReflexionMethodInvoke<T> {

  /**
   * apply
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: apply
   * @return
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  T apply() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
