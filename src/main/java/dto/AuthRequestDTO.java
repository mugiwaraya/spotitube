package dto;

import javax.json.bind.annotation.JsonbProperty;

public class AuthRequestDTO {
	@JsonbProperty("user")
	private String username;
	@JsonbProperty("password")
	private String password;

	public AuthRequestDTO()
	{

	}

	public AuthRequestDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

}

