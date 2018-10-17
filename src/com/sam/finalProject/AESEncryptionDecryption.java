package com.sam.finalProject;


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.sam.helper.bean.EncryptedDataBean;
import com.sam.helper.bean.ReadFileBean;

public class AESEncryptionDecryption {
	static Cipher cipher;

	public static  List<EncryptedDataBean> AES(List<ReadFileBean> unencryptedData) throws Exception {
		
		EncryptedDataBean encData;
		
		List<EncryptedDataBean> encryptedAESData = new ArrayList<EncryptedDataBean>();
		long start = System.currentTimeMillis();
		for(int i1=0;i1<unencryptedData.size();i1++){

    		encData = new EncryptedDataBean();
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    		keyGenerator.init(128);
    		SecretKey secretKey = keyGenerator.generateKey();
    		cipher = Cipher.getInstance("AES");
    		encData.setName(unencryptedData.get(i1).getName());
			encData.setEncEmailId(encrypt(unencryptedData.get(i1).getEmailId(), secretKey));
    		encData.setDepartment(unencryptedData.get(i1).getDepartment());
    		encData.setEncPassword(encrypt(unencryptedData.get(i1).getPassword(), secretKey));
    		encData.setAddress(unencryptedData.get(i1).getAddress());
    		encData.setEncSsn(encrypt(unencryptedData.get(i1).getSsn(), secretKey));
    		encryptedAESData.add(i1,encData);
		}
		System.out.println("test time data---"+(System.currentTimeMillis()-start));
		return encryptedAESData;   
	}
	
	public static String encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public static String decrypt(String encryptedText, SecretKey secretKey)
			throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}
	
