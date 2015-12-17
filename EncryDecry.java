package com.example.traitement;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import android.util.Base64;

public class EncryDecry {

	String ALGO="RSA";
	Cipher c;
	
	public EncryDecry () throws NoSuchAlgorithmException, NoSuchPaddingException
	{
		c = Cipher.getInstance(ALGO);
	}
	
	public String encry (RSAPublicKey pub, String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		c.init(Cipher.ENCRYPT_MODE, pub);
		byte[] encrypted = c.doFinal(msg.getBytes());
		String enc = Base64.encodeToString( encrypted, Base64.DEFAULT );
		return enc;
	}
	public String decry (RSAPrivateKey pri, String msg) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException
	{
		c.init(Cipher.DECRYPT_MODE,pri);
		byte[] decrypted = Base64.decode(msg, Base64.DEFAULT );
		decrypted = c.doFinal(decrypted);
		return new String(decrypted);
	}
}
