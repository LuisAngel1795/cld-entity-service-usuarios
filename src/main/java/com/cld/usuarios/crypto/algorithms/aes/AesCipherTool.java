package com.cld.usuarios.crypto.algorithms.aes;


import com.cld.usuarios.crypto.commons.AbstractCipherTool;

/**
 * AesCipherTool
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: AesCipherTool
 */
public class AesCipherTool extends AbstractCipherTool {
  /**
   * AesCipherTool
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: AesCipherTool
   */
  public AesCipherTool(String aesKey, String hmacKey, int mode){
    this.cipher = new AesObjectCipher(aesKey, hmacKey, mode);
  }
  /**
   * encrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: encrypt
   */
  @Override
  public <T> void encrypt(T dto) {
    this.iterateObject(dto);
  }
  /**
   * decrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: decrypt
   */
  @Override
  public <T> void decrypt(T dto) {
    this.encrypt(dto);
  }
}
