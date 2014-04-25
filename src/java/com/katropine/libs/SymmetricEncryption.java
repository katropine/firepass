
package com.katropine.libs;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author kriss
 */
public class SymmetricEncryption {
    
    private final String passphrase = "correct horse battery staple";
    
    private SecretKeySpec key;
    
    private byte[] ciphertext;
    private byte[] iv;
            
    public SymmetricEncryption() throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        byte[] salt = passphrase.getBytes();
        int iterations = 10100;
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey tmp = factory.generateSecret(new PBEKeySpec(passphrase.toCharArray(), salt, iterations, 256));
        this.key = new SecretKeySpec(tmp.getEncoded(), "AES");
    } 
    
    public void encrypt(String textBody) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, this.key);
        this.ciphertext = aes.doFinal(textBody.getBytes());
        this.iv = aes.getIV();
    }
    
    public String decrypt(byte[] ciphertext, byte[] ciphertextIv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aes.init(Cipher.DECRYPT_MODE, this.key, new IvParameterSpec(ciphertextIv));
        return new String(aes.doFinal(ciphertext));
    }

    public byte[] getCiphertext() throws UnsupportedEncodingException {
        return this.ciphertext; 
    }

    public byte[] getIv() {
        return this.iv;
    }
    
    
    
}
