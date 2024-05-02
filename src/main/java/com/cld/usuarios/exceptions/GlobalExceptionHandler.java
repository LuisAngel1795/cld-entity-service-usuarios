package com.cld.usuarios.exceptions;


import com.cld.usuarios.constants.UsuariosConstants;
import com.cld.usuarios.enums.EExceptionMessage;
import com.cld.usuarios.models.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  public GlobalExceptionHandler() {

  }

  /**
   * Errores bad request
   *
   * @return ResponseEntity<ApiError>
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationExceptions400Method(MethodArgumentNotValidException exception) {
    List<String> detalles = exception.getBindingResult().getFieldErrors().stream().map(this::generarMensajeCampo)
        .collect(Collectors.toList());

    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E400).getBody();

    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiError> handleValidationExceptions400Mismatch(MethodArgumentTypeMismatchException exception) {
    String nombreCampo = exception.getName();
    String mensaje = String.format(UsuariosConstants.EL_CAMPO_MAL_FORMATO, nombreCampo);
    List<String> detalles = new ArrayList<>();
    detalles.add(mensaje);
    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E400).getBody();
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  /**
   * @param exception
   * @return ResponseEntity<ApiError>
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleValidationExceptions400Constraint(ConstraintViolationException exception) {
    List<String> detalles = new ArrayList<>();

    String detailMessage = exception.getMessage();

    if (detailMessage.contains(":")) {
      detailMessage = detailMessage.split(":")[1].replaceFirst("^ *", "");
    }

    detalles.add(detailMessage);

    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E400).getBody();
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  /**
   * Errores UNAUTHORIZED
   *
   * @return ResponseEntity<ApiError>
   */
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(value = {AuthenticationException.class})
  public ResponseEntity<ApiError> handleValidationExceptions401Authentication(AuthenticationException exception) {
    List<String> detalles = new ArrayList<>();
    detalles.add(String.format(UsuariosConstants.UNAUTHORIZED_MESSAGE, exception.getMessage()));

    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E401).getBody();

    return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Errores not found
   *
   * @return ResponseEntity<ApiError>
   */
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = {NoHandlerFoundException.class})
  public ResponseEntity<ApiError> handleValidationExceptions404NoHandler(MethodArgumentNotValidException exception) {
    List<String> detalles = exception.getBindingResult().getFieldErrors().stream().map(this::generarMensajeCampo)
        .collect(Collectors.toList());

    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E404).getBody();

    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  /**
   * Errores not found
   *
   * @return ResponseEntity<ApiError>
   */
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = {MissingServletRequestParameterException.class})
  public ResponseEntity<ApiError> handleValidationExceptions404Servlet(MissingServletRequestParameterException exception) {
    List<String> detalles = new ArrayList<>();
    detalles.add(String.format(UsuariosConstants.REQUIRED_FIELD_MESSAGE, exception.getParameterName()));

    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E404).getBody();

    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  /**
   * Errores internal server error
   *
   * @return ResponseEntity<ApiError>
   */
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = {HttpServerErrorException.InternalServerError.class})
  public ResponseEntity<ApiError> handleValidationExceptions500Internal(HttpServerErrorException.InternalServerError exception) {
    List<String> detalles = new ArrayList<>();
    detalles.add(String.format(UsuariosConstants.SERVER_ERROR_MESSAGE, exception.getMessage()));

    ApiError apiError = new UsuariosException(detalles, EExceptionMessage.E404).getBody();

    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

//	/**
//	 * @param exception
//	 * @return ResponseEntity<ApiError>
//	 */
//	@ExceptionHandler(value = { ApiException.class })
//	private ResponseEntity<ApiError> handleApiRequestException(ApiException exception) {
//		switch (exception.getOption()) {
//		case E400:
//			return new ResponseEntity<>(exception.getBody(), HttpStatus.BAD_REQUEST);
//
//		case E401:
//			return new ResponseEntity<>(exception.getBody(), HttpStatus.UNAUTHORIZED);
//
//		case E404:
//			return new ResponseEntity<>(exception.getBody(), HttpStatus.NOT_FOUND);
//
//		default:
//			return new ResponseEntity<>(exception.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

  @ExceptionHandler(value = {UsuariosException.class})
  public ResponseEntity<ApiError> handleApiRequestException(UsuariosException e) {
    ResponseEntity<ApiError> response = null;

    // Generación error detallado
    switch (e.getOption()) {
      case E400:
        // Error 400
        response = new ResponseEntity<>(buildApiError(e), HttpStatus.BAD_REQUEST);
        break;
      case E401:
        // Error 400
        response = new ResponseEntity<>(buildApiError(e), HttpStatus.UNAUTHORIZED);
        break;
      case E401B:
        // Error 400
        response = new ResponseEntity<>(buildApiError(e), HttpStatus.UNAUTHORIZED);
        break;
      case E404:
        // Error 404
        response = new ResponseEntity<>(buildApiError(e), HttpStatus.NOT_FOUND);
        break;
      case E500:
        // Error 500
        response = new ResponseEntity<>(buildApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
        break;
      default:
        // Error default
        response = new ResponseEntity<>(e.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
        break;
    }

    return response;
  }

  public ApiError buildApiError(UsuariosException e) {
    ApiError apiError = new ApiError();
    apiError.setCodigo(e.getOption().getInfoExepcion().getCodigo());
    apiError.setMensaje(e.getOption().getInfoExepcion().getMensaje());
    apiError.setInfo(e.getOption().getInfoExepcion().getInfo());
    apiError.setDetalles(e.getBody().getDetalles());
    return apiError;
  }

  /**
   * Se construye str con msj de error
   *
   * @param error
   * @return String
   */
  private String generarMensajeCampo(FieldError error) {
    // Lectura de los errores
    String fieldName = error.getField();
    String errorMessage = error.getDefaultMessage();

    // Log error
    LOGGER.error("Campo :{} Error: {}", fieldName, errorMessage);
    return String.format(UsuariosConstants.REQUIRED_FIELD_MESSAGE, fieldName);
  }

}
