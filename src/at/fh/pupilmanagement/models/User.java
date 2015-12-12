package at.fh.pupilmanagement.models;

import java.util.HashMap;
import java.util.Map;

public class User {

	private String userName;
	private String password;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Map<String, String> getUserProperties() {
		Map<String, String> props = new HashMap<String, String>();

		props.put("javax.persistence.jdbc.user", this.userName);
		props.put("javax.persistence.jdbc.password", this.password);
		return props;
	}
}
