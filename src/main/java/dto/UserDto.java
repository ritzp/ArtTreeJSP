package dto;

import java.io.File;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

import jakarta.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.util.Base64;

public class UserDto {
	private String userId;
	private String email;
	private String phoneNumber;
	private String password;
	private String nickname;
	private String introduction;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getIcon(HttpServletRequest request) {
		String encoded = null;
		try {
			File source = new File(request.getServletContext().getRealPath("/data/user/icon/" + userId + ".png"));
			BufferedImage image = ImageIO.read(source);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			
			byte[] imageByte = baos.toByteArray();
			encoded = Base64.getEncoder().encodeToString(imageByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encoded;
	}
	
	/*public String getHeader(HttpServletRequest request) {
		String encoded = null;
		try {
			File source = new File(request.getServletContext().getRealPath("/data/user/header/" + userId + ".jpg"));
			BufferedImage image = ImageIO.read(source);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			
			byte[] imageByte = baos.toByteArray();
			encoded = Base64.getEncoder().encodeToString(imageByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encoded;
	}*/
}
