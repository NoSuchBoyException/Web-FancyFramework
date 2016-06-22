package org.fancy.framework.entities.impl;

import org.fancy.framework.entities.AbstractEntity;
import org.json.JSONObject;

public class UserEntity extends AbstractEntity {

	private String username;
	private String password;
	
	public UserEntity() {
		super();
	}
	
	public UserEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("username", getUsername());
		return json.toString();
	}
	
}
