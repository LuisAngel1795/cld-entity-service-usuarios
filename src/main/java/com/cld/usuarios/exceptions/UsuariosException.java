package com.cld.usuarios.exceptions;

import com.cld.usuarios.enums.EExceptionMessage;
import com.cld.usuarios.models.ApiError;
import com.cld.usuarios.models.ExepcionInfo;

import java.util.List;

public class UsuariosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final EExceptionMessage option;
	private final ApiError body;

	public UsuariosException(List<String> detalles, EExceptionMessage option) {
		this.option = option;
		ExepcionInfo info = option.getInfoExepcion();
		this.body = new ApiError(info.getCodigo(), info.getMensaje(),
				detalles);
	}

	/**
	 * @return EMensajeException
	 */
	public EExceptionMessage getOption() {
		return option;
	}

	/**
	 * @return ApiError
	 */
	public ApiError getBody() {
		return body;
	}


}

