package com.example.demo.model;

public class LogedUser {

	private static LogedUser instance = new LogedUser();
	private Long userId;
	private UlogaKorisnika userRole;
	
	public LogedUser() {
		
	}

	public LogedUser(Long userId, UlogaKorisnika userRole) {
		super();
		this.userId = userId;
		this.userRole = userRole;
	}

	public static LogedUser getInstance() {
		if (instance == null)
			instance = new LogedUser();
		
		return instance;
	}

	public static void setInstance(LogedUser instance) {
		LogedUser.instance = instance;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UlogaKorisnika getUserRole() {
		return userRole;
	}

	public void setUserRole(UlogaKorisnika userRole) {
		this.userRole = userRole;
	}
	
	
	
}
