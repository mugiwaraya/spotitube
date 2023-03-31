package dto;


import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "token", length = 255)
    private String token;
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @OneToMany(targetEntity = Playlist.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    public User(String username, String name, String email, String token) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.token = this.token != null ? this.token = token : "";
    }

    public User(String username, String name, String email, String token, String password, List<Playlist> playlists) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.playlists = playlists;
        this.token = this.token != null ? this.token = token : "";
    }

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setToken(String token) {
        this.token = token;
    }

    public void addPlaylist(Playlist playListToBeAdded) {
        this.playlists.add(playListToBeAdded);
    }

    public void deletePlaylist(int playListToBeDeletedId) {
        this.playlists.remove(playListToBeDeletedId);
    }
}