package util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class PasswordEncryptor {
	 private byte[] iv = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15};
	 private String frontPartKey = "0atolx85";

	 public String encrypt(String date, String value) {
		 try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec keySpec = new SecretKeySpec((frontPartKey+date).getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
			
			byte[] encrypted = cipher.doFinal((value).getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	 }
}
