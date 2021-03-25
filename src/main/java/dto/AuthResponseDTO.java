package dto;

import javax.json.bind.annotation.JsonbProperty;

public class AuthResponseDTO {

	@JsonbProperty("user")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonbProperty("token")
	private String token;

	public AuthResponseDTO(String username, String token) {
		this.username = username;
		this.token = token;
	}

}
