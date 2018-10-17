package com.sam.finalProject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sam.helper.bean.EncryptedDataBean;
import com.sam.helper.bean.ReadFileBean;

public class DESEncryptionDecryption {
	private static Cipher cipher = null;

	
public static  List<EncryptedDataBean> DES(List<ReadFileBean> unencryptedData) throws Exception {
		
		EncryptedDataBean encData;
		
		List<EncryptedDataBean> encryptedDESData = new ArrayList<EncryptedDataBean>();
		/*KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		// keysize must be equal to 112 or 168 for this provider
		keyGenerator.init(168);
		SecretKey secretKey = keyGenerator.generateKey();
		cipher = Cipher.getInstance("DESede");

		String plainText = "Java Cryptography Extension";
		System.out.println("Plain Text Before Encryption: " + plainText);

		byte[] plainTextByte = plainText.getBytes("UTF8");
		byte[] encryptedBytes = encrypt(plainTextByte, secretKey);

		String encryptedText = new String(encryptedBytes, "UTF8");
		System.out.println("Encrypted Text After Encryption: " + encryptedText);

		byte[] decryptedBytes = decrypt(encryptedBytes, secretKey);
		String decryptedText = new String(decryptedBytes, "UTF8");
		System.out.println("Decrypted Text After Decryption: " + decryptedText);*/
		for(int i1=0;i1<unencryptedData.size();i1++){
			
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
    		keyGenerator.init(168);
    		encData = new EncryptedDataBean();
    		SecretKey secretKey = keyGenerator.generateKey();
    		cipher = Cipher.getInstance("DESede");
    		encData.setName(unencryptedData.get(i1).getName());
    		System.out.println("encrypted data: "+toStringOutput(encrypt(toBytes(unencryptedData.get(i1).getEmailId()), secretKey)));
			encData.setEncEmailId(toStringOutput(encrypt(toBytes(unencryptedData.get(i1).getEmailId()), secretKey)));
    		encData.setDepartment(unencryptedData.get(i1).getDepartment());
    		encData.setEncPassword(toStringOutput(encrypt(toBytes(unencryptedData.get(i1).getPassword()), secretKey)));
    		encData.setAddress(unencryptedData.get(i1).getAddress());
    		encData.setEncSsn(toStringOutput(encrypt(toBytes(unencryptedData.get(i1).getSsn()), secretKey)));
    		
    		encryptedDESData.add(i1,encData);
		}
		return encryptedDESData;   
	}

	static String toStringOutput(byte[] encryptedBytes){
		String encryptedText = null;
		try {
			 encryptedText = new String(encryptedBytes, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedText;
		
	}
	static byte[] toBytes(String plainText) throws UnsupportedEncodingException{
		//String encryptedText;
		byte[] plainTextByte = plainText.getBytes("UTF8");

		return plainTextByte;
		
	}
	static byte[] encrypt(byte[] plainTextByte, SecretKey secretKey)
			throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(plainTextByte);
		return encryptedBytes;
	}

	static byte[] decrypt(byte[] encryptedBytes, SecretKey secretKey)
			throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return decryptedBytes;
	}
	
	
}
