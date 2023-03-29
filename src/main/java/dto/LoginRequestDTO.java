package dto;

import javax.json.bind.annotation.JsonbProperty;

public class LoginRequestDTO {
	@JsonbProperty("user")
	private String username;
	@JsonbProperty("password")
	private String password;

	public LoginRequestDTO()
	{

	}

	public LoginRequestDTO(String username, String password) {
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

