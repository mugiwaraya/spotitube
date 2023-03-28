package dto;


import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@Column(name = "username", nullable = false, length = 255)
	private String username;
	@Column(name = "email", nullable = false, length = 255)
	private String email;
	@Column(name = "token", nullable = false, length = 255)
	private String token;
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	@OneToMany(targetEntity = Playlist.class, cascade = CascadeType.ALL)
	private List<Playlist> playlists;



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
