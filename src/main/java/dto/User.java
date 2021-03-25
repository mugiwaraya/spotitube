package dto;

public class User {
	private int id;
	private String name;
	private String username;
	private String email;
	private String token;


	public User(int id, String username, String name, String email, String token) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.token = this.token != null ? this.token = token : "";
	}

	public User(int id, String username, String name) {
		this.id = id;
		this.username = username;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

}
