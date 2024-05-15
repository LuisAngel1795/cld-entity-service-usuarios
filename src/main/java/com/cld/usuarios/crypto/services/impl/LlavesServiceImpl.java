package com.cld.usuarios.crypto.services.impl;

import com.cld.usuarios.crypto.dao.LlavesDao;
import com.cld.usuarios.crypto.models.Llaves;
import com.cld.usuarios.crypto.util.AesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.cld.usuarios.crypto.constants.CryptoConstants.ZONE_ID;

@Service
public class LlavesServiceImpl implements ILlavesService{
    private static final Logger LOGGER = LoggerFactory.getLogger(LlavesServiceImpl.class);
    @Autowired
    private LlavesDao llavesDao;
    @Autowired
    private AesUtil aesUtil;

    public Llaves generateLlaves() {
        LOGGER.info("==>    GeneratingLlaves");
        Llaves llaves = buildLlaves();
        return llavesDao.save(llaves);
    }

    @Override
    public Llaves getLlaves(Long id) {
        return llavesDao.findById(id).orElse(null);
    }




    private Llaves buildLlaves() {
        //build current date and time
        ZonedDateTime dateUTC = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(ZONE_ID));


        return llaves;
    }
}
