package com.cld.usuarios.crypto.controller;

import com.cld.usuarios.crypto.constants.CryptoConstants;
import com.cld.usuarios.crypto.models.Llaves;
import com.cld.usuarios.crypto.services.impl.ILlavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CryptoConstants.SERVICE_BASEPATH_LLAVES)
public class LlavesController {

    @Autowired
    private ILlavesService llavesService;

    @GetMapping("/generar-llaves")
    public Llaves generarLlaves() {
        return llavesService.generateLlaves();
    }
    @GetMapping("/buscar-llaves")
    public Llaves generarLlaves(@RequestHeader HttpHeaders headers) {
        return llavesService.generateLlaves();
    }
}
