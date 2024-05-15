package com.cld.usuarios.crypto.algorithms.aes;

import com.gs.cipher.common.FieldAccessUtil;
import com.gs.cipher.common.ObjectCipher;
import com.gs.cipher.exception.CryptoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.function.Function;

/**
 * AesCipherTool
 * @author Angel Santander / Yorbin Nuñez
 * @ultimaModificacion - enero 2023
 * @version: V1.0.0.1
 * @description: AesCipherTool
 */
@Slf4j
public class AesObjectCipher implements ObjectCipher {

  // INDEX_0
  public static final Integer INDEX_0 = 0;
  // INITIALIZATION_VECTOR_SIZE
  public static final Integer INITIALIZATION_VECTOR_SIZE = 16;
  // AES_KEYSPEC
  public static final String AES_KEYSPEC = "AES";
  // H_MAC_SHA_256
  public static final String H_MAC_SHA_256 = "HmacSHA256";
  // AES_CBC_PKCS5_PADDING
  public static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
  // cipher
  private Cipher cipher;
  // hmac
  private Mac hmac;
  // iv
  private byte[] iv;
  // aesKey
  private SecretKeySpec aesKey;
  // operation
  private Function<byte[], byte[]> operation;
  // inputMapper
  private Function<String, byte[]> inputMapper;
  // ouputMapper
  private Function<byte[], String> ouputMapper;

  ThreadLocal<Mac> macThreadLocal;

  /**
   * AesObjectCipher
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: AesObjectCipher
   * @param aesKey
   * @param hmacKey
   * @param mode
   */
  public AesObjectCipher(String aesKey, String hmacKey, int mode){
    this.macThreadLocal = new ThreadLocal<>();
    SecretKeySpec aesKeySpec =
        new SecretKeySpec(Base64.decodeBase64(aesKey.getBytes(StandardCharsets.UTF_8)), AES_KEYSPEC);
    SecretKeySpec hmacKeySpec =
        new SecretKeySpec(Base64.decodeBase64(hmacKey.getBytes(StandardCharsets.UTF_8)), H_MAC_SHA_256);

    switch (mode){
      case Cipher.ENCRYPT_MODE:
        this.initEncrypt(aesKeySpec, hmacKeySpec);
        break;
      case Cipher.DECRYPT_MODE:
        this.initDecrypt(aesKeySpec, hmacKeySpec);
        break;
      default:
        throw new CryptoException("Modo inválido");
    }

  }

  /**
   * initEncrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description:  initEncrypt
   * @param aesKey
   * @param hmacKey
   */
  private void initEncrypt(SecretKeySpec aesKey, SecretKeySpec hmacKey){
    this.operation = this::encrypt;
    this.inputMapper = String::getBytes;
    this.ouputMapper = Base64::encodeBase64String;
    this.iv = new byte[INITIALIZATION_VECTOR_SIZE];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(this.iv);
    try {
      this.hmac = Mac.getInstance(H_MAC_SHA_256);
      this.hmac.init(hmacKey);
      this.cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
      this.cipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(this.iv));
    }
    catch (NoSuchAlgorithmException|
           InvalidAlgorithmParameterException| NoSuchPaddingException| InvalidKeyException e) {
      log.error(String.valueOf(e));
      throw new CryptoException(e.getMessage());
    }
  }

  /**
   * initDecrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: initDecrypt
   * @param aesKey
   * @param hmacKey
   */
  private void initDecrypt(SecretKeySpec aesKey, SecretKeySpec hmacKey){
    this.operation = this::decrypt;
    this.inputMapper = Base64::decodeBase64;
    this.ouputMapper = String::new;
    this.aesKey = aesKey;
    try {
      this.hmac = Mac.getInstance(H_MAC_SHA_256);
      this.hmac.init(hmacKey);
    }
    catch (NoSuchAlgorithmException|InvalidKeyException e) {
      log.error(String.valueOf(e));
      throw new CryptoException(e.getMessage());
    }
  }

  Mac getMacclone(){
    try{
      return (Mac) this.hmac.clone();
    }
    catch (Exception e){
      log.error("Error al clonar");
      throw new CryptoException("Error al clonar herramienta hash hmac");
    }
  }

  /**
   * encrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: encrypt
   * @param plain
   * @return
   */
  public byte[] encrypt(byte[] plain){
    byte[] cipherText;
    //Mac mac = this.macThreadLocal.get();
    /*if(mac == null){
      log.info("Clonando mac por primera vez");
      mac = this.getMacclone();
      this.macThreadLocal.set(mac);
    }*/

    try {
      cipherText = cipher.doFinal(plain);
      byte[] ivCipherText = AesObjectCipher.concatenateBytes(iv, cipherText);
      //byte[] hmacHash = mac.doFinal(ivCipherText);
      byte[] hmacHash = this.hmac.doFinal(ivCipherText);
      return AesObjectCipher.concatenateBytes(ivCipherText, hmacHash);
    }
    catch (IllegalBlockSizeException|BadPaddingException e) {
      log.error(String.valueOf(e));
      throw new CryptoException("Error al cifrar con AES");
    }
  }

  /**
   * decrypt
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: decrypt
   * @param encrypted
   * @return
   */
  public byte[] decrypt(byte[] encrypted) {

    Mac mac;
    try{
      mac = (Mac) this.hmac.clone();
    }
    catch (Exception e){
      log.error("Error al clonar");
      throw new CryptoException("Error al clonar herramienta hash hmac");
    }
    int macLength = mac.getMacLength();

    if(encrypted.length < INITIALIZATION_VECTOR_SIZE + macLength){
      String detail = "No tiene bytes para descifrar";
      log.error(detail);
      throw new CryptoException(detail);
    }

    byte[] ivRecuperado = Arrays.copyOf(encrypted, INITIALIZATION_VECTOR_SIZE);
    int cipherTextLength = encrypted.length - macLength;
    byte[] cipherText = Arrays.copyOfRange(encrypted, INITIALIZATION_VECTOR_SIZE, cipherTextLength);
    byte[] ivCipherText = AesObjectCipher.concatenateBytes(ivRecuperado, cipherText);
    byte[] hash = Arrays.copyOfRange(encrypted, cipherTextLength, encrypted.length);
    byte[] calculatedHash = mac.doFinal(ivCipherText);
    if (!Arrays.equals(hash, calculatedHash)) {
      throw new CryptoException("Hash inválido");
    }
    Cipher decipher;
    try {
      decipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
      decipher.init(Cipher.DECRYPT_MODE, this.aesKey, new IvParameterSpec(ivRecuperado));
      return decipher.doFinal(cipherText);
    }
    catch (NoSuchAlgorithmException| NoSuchPaddingException|
           InvalidKeyException| InvalidAlgorithmParameterException|IllegalBlockSizeException| BadPaddingException e) {
      log.error(String.valueOf(e));
      throw new CryptoException("Error al decifrar con AES");
    }

  }

  /**
   * apply
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description:  apply
   * @param field
   * @param object
   */
  @Override
  public void apply(Field field, Object object) {
    String encryptedValue = this.apply((String) FieldAccessUtil.getFieldValue(field, object));
    log.debug("campo "+ field.getName() + ": valor: "+encryptedValue);
    FieldAccessUtil.setField(field,object,encryptedValue);
  }

  /**
   * apply
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: apply
   * @param value
   * @return
   */
  @Override
  public String apply(String value) {
    return this.ouputMapper.apply(this.operation.apply(this.inputMapper.apply(value)));
  }

  /**
   * concatenateBytes
   * @author Angel Santander / Yorbin Nuñez
   * @ultimaModificacion - enero 2023
   * @version: V1.0.0.1
   * @description: concatenateBytes
   * @param first
   * @param second
   * @return
   */
  private static byte[] concatenateBytes(byte[] first, byte[] second) {
    byte[] concatBytes = new byte[first.length + second.length];
    System.arraycopy(first, 0, concatBytes, INDEX_0, first.length);
    System.arraycopy(second, INDEX_0, concatBytes, first.length, second.length);
    return concatBytes;

  }
}
