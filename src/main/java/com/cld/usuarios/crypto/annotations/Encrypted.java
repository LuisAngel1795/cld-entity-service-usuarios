package com.cld.usuarios.crypto.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Encrypted
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: Encrypted
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Encrypted {

}
