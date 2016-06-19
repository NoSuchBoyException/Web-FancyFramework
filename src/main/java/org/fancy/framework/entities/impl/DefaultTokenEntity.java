package org.fancy.framework.entities.impl;

import org.fancy.framework.entities.AbstractEntity;
import org.json.JSONObject;

public class DefaultTokenEntity extends AbstractEntity {

	private String token;

	public DefaultTokenEntity() {}

	public DefaultTokenEntity(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("token", getToken());
		return json.toString();
	}
	
}
