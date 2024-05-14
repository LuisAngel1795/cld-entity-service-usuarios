package com.cld.usuarios.aspect;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UtilAspect {
    boolean activa() default true;

    boolean desencriptaBody() default false;

    boolean validaBody() default false;

    boolean validaParams() default false;

    boolean bodyDinamico() default false;

    boolean encriptaResponse() default false;
}
