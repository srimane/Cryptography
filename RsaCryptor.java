package com.example.traitement;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import android.util.Base64;

public class RsaCryptor {
	
	String ALGO="RSA";
	KeyPairGenerator keyGen;
	KeyPair pair;
	String msg;
	Cipher c;
	byte[] encrypted;
	String enc;
	String p ;
	PrivateKey pk;
	
	public RsaCryptor(String msg)throws Exception
	{
		this.msg=msg;
		keyGen = KeyPairGenerator.getInstance(ALGO) ;
		pair = keyGen.genKeyPair();
		c = Cipher.getInstance(ALGO);
		p=Base64.encodeToString(pair.getPrivate().getEncoded(), Base64.DEFAULT);
	}
	public RSAPublicKey getPublic ()
	{
		return (RSAPublicKey)pair.getPublic();
	}
	public String encrypt()throws Exception
	{
		
		c.init(Cipher.ENCRYPT_MODE, pair.getPublic());
		
		encrypted = c.doFinal(msg.getBytes());
		enc = Base64.encodeToString( encrypted, Base64.DEFAULT );
		
		return enc;
	}
	public String decrypt() throws Exception
	{
		pk = new PrivateKey() {
			
			@Override
			public String getFormat() {
				// TODO Auto-generated method stub
				return "PKCS#8";
			}
			
			@Override
			public byte[] getEncoded() {
				// TODO Auto-generated method stub
				return Base64.decode(p.getBytes(), Base64.DEFAULT);
			}
			
			@Override
			public String getAlgorithm() {
				// TODO Auto-generated method stub
				return "RSA";
			}
		};
		
		c.init(Cipher.DECRYPT_MODE,pk);
		byte[] decrypted = Base64.decode(enc, Base64.DEFAULT );
		decrypted = c.doFinal(encrypted);

		return new String(decrypted);
	}
}
