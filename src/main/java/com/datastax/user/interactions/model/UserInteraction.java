package com.datastax.user.interactions.model;

import java.util.Date;

public class UserInteraction {

	private String userId;
	private String app;
	private Date time;
	private String action;
	public UserInteraction(String userId, String app, Date time, String action) {
		super();
		this.userId = userId;
		this.app = app;
		this.time = time;
		this.action = action;
	}
	public String getUserId() {
		return userId;
	}
	public String getApp() {
		return app;
	}
	public Date getTime() {
		return time;
	}
	public String getAction() {
		return action;
	}
	@Override
	public String toString() {
		return "UserInteraction [userId=" + userId + ", app=" + app + ", time=" + time + ", action=" + action + "]";
	}
}
