package com.datastax.user.interactions.model;

import java.util.Date;
import java.util.UUID;

public class UserInteraction {

	private UUID visitId;
	private String userId;
	private String app;
	private Date time;
	private String action;
	
	public UserInteraction(UUID visitId, String userId, String app, Date time, String action) {
		super();
		this.userId = userId;
		this.visitId = visitId;
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
	public UUID getVisitId() {
		return visitId;
	}
	@Override
	public String toString() {
		return "UserInteraction [visitId=" + visitId.toString() + ", userId=" + userId + ", app=" + app + ", time=" + time
				+ ", action=" + action + "]";
	}
}
