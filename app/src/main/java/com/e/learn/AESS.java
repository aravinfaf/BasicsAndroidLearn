package com.e.learn;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESS {

    private static String factoryInstance = "PBKDF2WithHmacSHA256";
    private static String cipherInstance = "AES/CBC/PKCS5PADDING";
    private static String secretKeyType = "AES";
    private static byte[] ivCode = new byte[16];
    private static String secretKey = "yourSecretKey";

    public static String encrypt(String secretKey, String salt, String value) throws Exception {
        Cipher cipher = initCipher(secretKey, salt, Cipher.ENCRYPT_MODE);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        byte[] cipherWithIv = addIVToCipher(encrypted);
        return android.util.Base64.encodeToString(cipherWithIv, android.util.Base64.NO_WRAP);
    }
    public static String decrypt(String secretKey, String salt, String encrypted) throws Exception {

        Cipher cipher = initCipher(secretKey, salt, Cipher.DECRYPT_MODE);
        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
        // unpad
        byte[] originalWithoutIv = Arrays.copyOfRange(original, 16, original.length);
        return new String(originalWithoutIv);
    }
    private static Cipher initCipher(String secretKey, String salt, int mode) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(factoryInstance);
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec skeySpec = new SecretKeySpec(tmp.getEncoded(), secretKeyType);
        Cipher cipher = Cipher.getInstance(cipherInstance);
        // Generating random IV
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivCode);

        cipher.init(mode, skeySpec, new IvParameterSpec(ivCode));
        return cipher;
    }
    public static void main(String[] args) throws Exception {
        String fSalt = "anySaltYouCanUseOfOn";
        String plainText = "M0993000353";
        String cipherText = encrypt(secretKey, fSalt, plainText);
        System.out.println("Cipher: " + cipherText);
        String dcrCipherText = decrypt(secretKey, fSalt, cipherText);
        System.out.println("Decrypted: " + dcrCipherText);
    }

    private static byte[] addIVToCipher(byte[] encrypted) {
        byte[] cipherWithIv = new byte[ivCode.length + encrypted.length];
        System.arraycopy(ivCode, 0, cipherWithIv, 0, ivCode.length);
        System.arraycopy(encrypted, 0, cipherWithIv, encrypted.length, encrypted.length);
        return cipherWithIv;
    }

}
