package dao;

import domain.Playlist;
import interfaces.IPlaylist;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDAO implements IPlaylist {

    private Connection conn;
    private final static Logger LOGGER = Logger.getLogger(PlaylistDAO.class.getName());

    @Inject
    public PlaylistDAO(DatabaseConnection databaseConnection) {
        this.conn = databaseConnection.getConnection();
    }

    @Override
    public List<Playlist> getAllPlaylists(int userId) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlists WHERE ownerId = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                playlists.add(new Playlist(rs.getInt("id"), rs.getString("name"), rs.getInt("ownerId")));
            }
            stm.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Something went wrong with getting all the playlists from the DB: " + e);
        }
        return playlists;
    }

    @Override
    public Playlist getPlaylist(int playlistId) {
        Playlist playlist = null;
        String query = "SELECT * FROM playlists WHERE id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, playlistId);
            ResultSet rs = stm.executeQuery();
            stm.close();
            if (rs.next()) {
                playlist = new Playlist(rs.getInt("id"), rs.getString("name"), rs.getInt("ownerId"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong with getting the specified playlist from the DB: " + e);
        }
        return playlist;
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong with deleting the specified playlist from the DB: " + e);
            return false;
        }
    }

    @Override
    public boolean addPlaylist(Playlist playlist) {
        String query = "INSERT INTO playlists (name, owner)\n" +
                "VALUES (?, ?)";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, playlist.getName());
            stm.setInt(2, playlist.getOwnerId());
            stm.executeUpdate();
            stm.close();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong with adding a playlist to the DB: " + e);
            return false;
        }
    }
}
