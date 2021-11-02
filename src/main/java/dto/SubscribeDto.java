package dto;

public class SubscribeDto {
	String subscribingUserId;
	String subscribedUserId;
	
	public String getSubscribingUserId() {
		return subscribingUserId;
	}
	
	public void setSubscribingUserId(String subscribingUserId) {
		this.subscribingUserId = subscribingUserId;
	}
	
	public String getSubscribedUserId() {
		return subscribedUserId;
	}
	
	public void setSubscribedUserId(String subscribedUserId) {
		this.subscribedUserId = subscribedUserId;
	}	
}
