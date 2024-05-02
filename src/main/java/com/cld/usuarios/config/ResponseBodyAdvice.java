package com.cld.usuarios.config;


import com.cld.usuarios.exceptions.GlobalExceptionHandler;
import com.cld.usuarios.models.GenericResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice<Object> {

  private static final String PACKAGE = "com.cld.usuarios";


  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    // Validación si se debe excluir filter
    return !StringUtils.containsIgnoreCase(returnType.getContainingClass().getPackageName(),
        GlobalExceptionHandler.class.getPackageName());
  }



  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                ServerHttpResponse response) {
    // Revisión si se agregará formato a la respuesta
    if (StringUtils.contains(returnType.getContainingClass().getPackageName(), PACKAGE)) {
      GenericResponse bodyResponse = new GenericResponse<>();
      bodyResponse.setMensaje("Operación exitosa.");
      bodyResponse.setFolio(MDC.get("currentFolio").replace(" - ", ""));
      if (ObjectUtils.allNotNull(body)){
        bodyResponse.setResultado(body);
      }
      body = bodyResponse;
    }
    return body;
  }

}
