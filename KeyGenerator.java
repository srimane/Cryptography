package com.example.traitement;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.NoSuchPaddingException;

import android.util.Base64;


public class KeyGenerator {
	String ALGO="RSA";
	KeyPairGenerator keyGen;
	KeyPair pair;

	RSAPublicKey pub;
	RSAPrivateKey priv;
	
	public KeyGenerator() {
		// TODO Auto-generated constructor stub
		
		try {
			keyGen = KeyPairGenerator.getInstance(ALGO) ;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pair = keyGen.genKeyPair();
		pub = (RSAPublicKey)pair.getPublic();
		priv = (RSAPrivateKey)pair.getPrivate();
		
	}
	public RSAPublicKey getPublic ()
	{
		return (RSAPublicKey)pair.getPublic();
	}
	public String getModulus()
	{
		return pub.getModulus().toString();
	}
	public String getExponend()
	{
		return pub.getPublicExponent().toString();
	}
	public String getencoded()
	{
		return Base64.encodeToString(pub.getEncoded(), Base64.DEFAULT);
	}
	public String getPrivEncoded() 
	{
		return Base64.encodeToString(priv.getEncoded(), Base64.DEFAULT);
	}
	public String getPrivateExponent()
	{
		return priv.getPrivateExponent().toString();
	}
	public String getPrivModulus() 
	{
		return priv.getModulus().toString();
	}	
}
