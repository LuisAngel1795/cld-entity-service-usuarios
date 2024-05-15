package com.cld.usuarios.crypto.commons;

import com.gs.cipher.common.ReflexionMethodInvoke;
import com.gs.cipher.exception.CryptoException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * FieldAccessUtil
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: FieldAccessUtil
 */
@Slf4j
public final class FieldAccessUtil {

  /**
   * FieldAccessUtil
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: FieldAccessUtil
   */
  private FieldAccessUtil(){
    // private constructor
  }
  /**
   * reflexionExceptionTreatment
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: reflexionExceptionTreatment
   * @param callback
   * @param fieldName
   * @param methodName
   * @return
   */
  public static Object reflexionExceptionTreatment(
          ReflexionMethodInvoke<Object> callback, String fieldName, String methodName
  ){
    try{
      return callback.apply();
    }
    catch (NoSuchMethodException exception){
      String detail = "No se encontró el getter del campo " + fieldName;
      log.error(detail);
      throw new CryptoException(detail);
    }
    catch (InvocationTargetException e) {
      String detail = "Error al ejecutar el getter del campo " + fieldName + "{}";
      log.error(detail, e);
      throw new CryptoException(detail);
    }
    catch (IllegalAccessException e) {
      String detail = "El getter del campo " + fieldName + " no es accesible : {}";
      log.error(detail, e);
      throw new CryptoException(detail);
    }
  }

  /**
   * setField
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: setField
   * @param field
   * @param object
   * @param encrypted
   */
  public static void setField(Member field, Object object, String encrypted) {
    String fieldName = field.getName();
    String fieldSetterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    ReflexionMethodInvoke<Object> function = () -> {
      Method setter = object.getClass().getDeclaredMethod(fieldSetterName, String.class);
      if(!setter.canAccess(object)){
        String detail = "El setter del campo " + fieldName + " no es accesible";
        log.error(detail);
        throw new CryptoException(detail);
      }
      setter.invoke(object, encrypted);
      return null;
    };
    reflexionExceptionTreatment(function, fieldName, fieldSetterName);
  }

  /**
   * getFieldValue
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: getFieldValue
   * @param field
   * @param object
   * @return
   */
  public static Object getFieldValue(Member field, Object object) {
    String fieldName = field.getName();
    String fieldGetterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

    ReflexionMethodInvoke<Object> callback = () -> {
      Method getter = object.getClass().getDeclaredMethod(fieldGetterName);
      if(!getter.canAccess(object)){
        String detail = "El setter del campo " + fieldName + " no es accesible";
        log.error(detail);
        throw new CryptoException(detail);
      }
      return getter.invoke(object);

    };
    return reflexionExceptionTreatment(callback, fieldName, fieldGetterName);

  }

}
