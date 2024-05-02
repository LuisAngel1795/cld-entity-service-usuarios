package com.cld.usuarios.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Order(1)
public class TraceFilter extends OncePerRequestFilter {

  public static final String TRACE_ID = "traceId";
  private static final Logger LOGGER = LoggerFactory.getLogger(TraceFilter.class);

  @Value("${ZONE_ID}")
  private String ZONE_ID;

  @Value("${DATE_FORMAT_FOLIO}")
  private String DATE_FORMAT_FOLIO;
  @Value("${spring.application.name}")
  private String API;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String currentFolio = this.getFolioWithIdMSO();
    String path = request.getRequestURI().toString();
    String method = request.getMethod().toString();
    MDC.put("currentFolio", currentFolio);
    long tiempoInicio = System.currentTimeMillis();
    MDC.put(TRACE_ID, currentFolio);
    LOGGER.info("****************** Inicia Peticion. API={} uri={} method={} **********************", API,path, method);
    filterChain.doFilter(request, response);
    MDC.put("tiempoTotal", String.valueOf((System.currentTimeMillis() - tiempoInicio)));
    LOGGER.info("****************** Termina Peticion. API={} uri={} method={} *********************", API, path, method);
    MDC.remove("tiempoTotal");
  }



  private String getFolioWithIdMSO() {
    return ZonedDateTime.now().withZoneSameInstant(ZoneId.of(ZONE_ID))
        .format(DateTimeFormatter.ofPattern(DATE_FORMAT_FOLIO));
  }

}
