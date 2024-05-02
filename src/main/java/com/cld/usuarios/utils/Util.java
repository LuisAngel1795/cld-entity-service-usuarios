package com.cld.usuarios.utils;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


/**
 * <b>Util.java</b>
 * 
 * @version: cld-entity-service-usuarios-v1 1.0
 * @descripcion: Clase Util
 * @author: Luis Silva
 */
@Component
public class Util {
	/**
	 * Get folio en string.
	 *
	 * @return folio
	 */
	public String getFolio() {
		String currentFolio = UUID.randomUUID().toString();
		MDC.put("currentFolio", currentFolio);
		return currentFolio;
	}


	/**
	 * Get fecha  en string.
	 *
	 * @return fecha-hora-generaci??n
	 */
	public Date getDate(){
		Date date = Date.valueOf(LocalDate.now());
		return date;
	}
}
