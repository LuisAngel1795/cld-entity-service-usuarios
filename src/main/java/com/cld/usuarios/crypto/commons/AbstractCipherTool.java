package com.cld.usuarios.crypto.commons;

import com.gs.cipher.annotations.Encrypted;
import com.gs.cipher.common.FieldAccessUtil;
import com.gs.cipher.common.ObjectCipher;
import com.gs.cipher.exception.CryptoException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * AbstractCipherTool
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: AbstractCipherTool
 */
@Slf4j
public abstract class AbstractCipherTool {
  protected ObjectCipher cipher;

  /**
   * encrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: encrypt
   * @param dto
   * @param <T>
   */
  public abstract <T> void encrypt(T dto);

  /**
   * decrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: decrypt
   * @param dto
   * @param <T>
   */
  public abstract <T> void decrypt(T dto);

  /**
   * validateType
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: validateType
   * @param dto
   * @param field
   */
  protected void validateType(Object dto, Field field){
    Class<?> clazz = field.getType();
    boolean isEncrypted = field.isAnnotationPresent(Encrypted.class);
    try {
      if (!isEncrypted){
        return;
      }
      if(clazz == String.class){
        String valorCampo = (String) com.gs.cipher.common.FieldAccessUtil.getFieldValue(field, dto);
        if (valorCampo != null && !valorCampo.isEmpty()) {
          this.cipher.apply(field, dto);
        }
        return;
      }

      if(AbstractCipherTool.isList(clazz)) {
        this.iterateList((List<Object>) com.gs.cipher.common.FieldAccessUtil.getFieldValue(field, dto));
      }
      else {
        this.iterateObject(FieldAccessUtil.getFieldValue(field, dto));
      }
    }
    catch (Exception e) {
      log.error(String.valueOf(e));
      throw new CryptoException(e.getMessage());
    }
  }

  /**
   * iterateObject
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: iterateObject
   * @param dto
   */
  protected void iterateObject(Object dto) {
    if (dto == null) {
      return;
    }

    for (Field field : dto.getClass().getDeclaredFields()) {
      this.validateType(dto, field);
    }
  }

  /**
   * iterateList
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: iterateList
   * @param list
   */
  private void iterateList(List<Object> list) {
    if (list == null || list.isEmpty()) {
      return;
    }
    if (list.get(0) != null && list.get(0).getClass() == String.class) {
      list.replaceAll((Object value) -> {
        String item = (String)value;
        if(item == null || item.isEmpty()){
          return item;
        }
        return this.cipher.apply(item);
      });
    }
    else {
      for (Object object : list) {
        this.iterateObject(object);
      }
    }
  }

  /**
   * isList
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: isList
   * @param clazz
   * @return
   */
  private static boolean isList(Class<?> clazz) {
    return List.class.isAssignableFrom(clazz);
  }




}
