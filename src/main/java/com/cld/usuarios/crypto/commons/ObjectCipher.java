package com.cld.usuarios.crypto.commons;

import java.lang.reflect.Field;

/**
 * ObjectCipher
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: ObjectCipher
 */
public interface ObjectCipher {
  /**
   * apply
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: apply
   * @param field
   * @param object
   */
  void apply(Field field, Object object);

  /**
   * apply
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: apply
   * @param value
   * @return
   */
  String apply(String value);
}
