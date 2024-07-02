/*
package com.cld.usuarios.utils;

import com.gs.cipher.algorithms.rsa.RsaCipherTool;
import com.gs.cipher.exception.CryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;


@Component
public class CryptoUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtil.class);

    public void decryptAES(Accesos accesos, Object body) {
        try {
            RsaCipherTool rsaEncrypt = new RsaCipherTool(accesos.getAccesoPrivado(), Cipher.DECRYPT_MODE);
            rsaEncrypt.decrypt(body);
        }
        catch (CryptoException e) {
            LOGGER.info("Detalle al momento de descifrar {}", e);
        }
    }


    public void encryptAES(Accesos accesos, Object body) {
        try {
            RsaCipherTool rsaEncrypt = new RsaCipherTool(accesos.getAccesoPublico(), Cipher.ENCRYPT_MODE);
            rsaEncrypt.encrypt(body);
        }
        catch (CryptoException e) {
            LOGGER.info("Detalle al momento de cifrar {}", e);
        }


    }
}
*/
