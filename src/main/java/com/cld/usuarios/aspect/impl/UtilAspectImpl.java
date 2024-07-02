/*
package com.cld.usuarios.aspect.impl;

import com.cld.usuarios.aspect.UtilAspect;
import com.cld.usuarios.utils.CryptoUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class UtilAspectImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilAspectImpl.class);

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private CryptoUtil cryptoUtil;

    */
/*@Autowired
    private*//*


    @Around("@annotation(com.gs.ncc.loan.aspect.ValidatorCriptoAspect)")
     Object ValidatorCripto(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        UtilAspect anotacion = method.getAnnotation(UtilAspect.class);
        HttpHeaders headers = (HttpHeaders) getData(parameters, args, RequestHeader.class);


        Boolean activa = anotacion.activa();
        if (Boolean.TRUE.equals(activa)) {
            before(joinPoint);
        }
        Object result = joinPoint.proceed();
        Boolean encriptaResponse = anotacion.encriptaResponse();
        if (Boolean.TRUE.equals(activa)) {
            if (Boolean.TRUE.equals(encriptaResponse)) {
                //String idAcceso = headers.getFirst(X_ID_ACCESO);
                //Accesos accesos = apiSeguridad.getAccesos(idAcceso);
                cryptoUtil.encryptRSA(accesos, result);
            }
        }
        return result;
    }

    private Object getData(Parameter[] parameters, Object[] args, Class<? extends Annotation> clazz) {
        Optional<Object> requestBody = Arrays.stream(parameters)
                .filter(parameter -> parameter.isAnnotationPresent(clazz))
                .map(parameter -> args[Arrays.asList(parameters).indexOf(parameter)])
                .findFirst();
        return requestBody.orElse(null);
    }

    private void before(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        Parameter[] parameters = method.getParameters();
        HttpHeaders headers = (HttpHeaders) getData(parameters, args, RequestHeader.class);
        ValidatorCriptoAspect anotacion = method.getAnnotation(ValidatorCriptoAspect.class);
        Boolean validaBody = anotacion.validaBody();
        Boolean validaParams = anotacion.validaParams();
        Boolean bodyDinamico = anotacion.bodyDinamico();
        Boolean desencriptaBody = anotacion.desencriptaBody();
        Object body = getData(parameters, args, RequestBody.class);

        if (Boolean.TRUE.equals(desencriptaBody)) {
            String idAcceso = headers.getFirst(X_ID_ACCESO);
            Accesos accesos = apiSeguridad.getAccesos(idAcceso);
            cipherUtil.decryptRSA(accesos, body);
            LOGGER.info("Informacion en claro {}", new Gson().toJson(body));
        }
        if (Boolean.TRUE.equals(validaParams)) {
            validarParams(target, method, args);
        }
        if (Boolean.TRUE.equals(validaBody)) {
            validaBody(body, bodyDinamico);
        }
    }
}*/
