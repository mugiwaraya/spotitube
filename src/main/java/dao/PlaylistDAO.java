package dao;

import dto.AddPlayListDTO;
import dto.Playlist;
import dto.PlaylistsDTO;
import dto.User;
import interfaces.IPlaylistDAO;
import repository.PlaylistRepository;

import javax.inject.Inject;

public class PlaylistDAO implements IPlaylistDAO {

    private PlaylistRepository playlistRepository;

    @Inject
    public PlaylistDAO() {
        this.playlistRepository = new PlaylistRepository();
    }

    @Override
    public PlaylistsDTO getAllPlaylistsForUser(User user) {
        return  playlistRepository.getAllPlaylists(user);
    }

    @Override
    public PlaylistsDTO getAllPlaylists() {
        return null;
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        return false;
    }

    @Override
    public AddPlayListDTO addPlaylist(AddPlayListDTO playlist) {
        playlist = playlistRepository.addPlaylist(playlist);
        return playlist;
    }

    @Override
    public Playlist editPlaylist(Playlist playlist, int playlistId) {
        return null;
    }

//
//	private Connection conn;
//	private final static Logger LOGGER = Logger.getLogger(PlaylistDAO.class.getName());
//
//	@Inject
//	public PlaylistDAO(IDatabaseConnection databaseConnection) throws Exception {
//		this.conn = databaseConnection.getConnection();
//	}
//
//	@Override
//	public PlaylistsDTO getAllPlaylists(int userId) {
//		List<Playlist> playlists = new ArrayList<>();
//		String query = "select  playlists.id, playlists.name, playlists.ownerId, sum(tracks.duration) as length\n" +
//				"from `playlists`\n" +
//				"left join trackinplaylist on playlists.id = trackinplaylist.playlistId\n" +
//				"left join tracks on tracks.id = trackinplaylist.trackId\n" +
//				"where playlists.ownerId = ? \n" +
//				"group by playlists.id";
//		int totalLength = 0;
//		try {
//			PreparedStatement stm = conn.prepareStatement(query);
//			stm.setInt(1, userId);
//			ResultSet rs = stm.executeQuery();
//			while (rs.next()) {
//				playlists.add(new Playlist(rs.getInt("id"), rs.getString("name"), rs.getInt("ownerId") == userId, null));
//				totalLength += rs.getInt("length");
//			}
//
//			stm.close();
//		} catch (Exception e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with getting all the playlists from the DB: " + e);
//		}
//
//		return new PlaylistsDTO(playlists, totalLength);
//	}
//
//
//	@Override
//	public boolean deletePlaylist(int playlistId) {
//		String sql = "DELETE FROM playlists WHERE id = ?";
//		try {
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setInt(1, playlistId);
//			statement.executeUpdate();
//			statement.close();
//			return true;
//		} catch (SQLException e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with deleting the specified playlist from the DB: " + e);
//			return false;
//		}
//	}
//
//
//	@Override
//	public AddPlayListDTO addPlaylist(AddPlayListDTO playlist) {
//		String query = "INSERT INTO playlists (name, ownerId)\n" +
//				"VALUES (?, ?)";
//		try {
//			PreparedStatement stm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			stm.setString(1, playlist.getName());
//			stm.setInt(2, playlist.getOwnerId());
//			stm.executeUpdate();
//			try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
//				if (generatedKeys.next()) {
//					playlist.setId(generatedKeys.getInt(1));
//				} else {
//					throw new SQLException("Creating playlist failed, no ID obtained.");
//				}
//			}
//			stm.close();
//
//		} catch (SQLException e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with adding a playlist to the DB: " + e);
//			return null;
//		}
//		return playlist;
//	}
//
//	@Override
//	public Playlist editPlaylist(Playlist playlist, int playlistId) {
//		String query = "UPDATE playlists " +
//				"SET name = ? \n" +
//				"WHERE id = ?";
//		try {
//			PreparedStatement stm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			stm.setString(1, playlist.getName());
//			stm.setInt(2, playlist.getId());
//			stm.executeUpdate();
//			stm.close();
//		} catch (SQLException e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with updating the playlist in the DB: " + e);
//			return null;
//		}
//		return playlist;
//	}
}
