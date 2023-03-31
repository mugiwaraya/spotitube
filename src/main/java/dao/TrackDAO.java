package dao;

import dto.Track;
import dto.Tracks;
import exceptions.DeleteException;
import exceptions.InsertionException;
import exceptions.TrackException;
import interfaces.ITrackDAO;
import repository.PlaylistRepository;
import repository.TrackRepository;

import javax.inject.Inject;

public class TrackDAO implements ITrackDAO {

    private TrackRepository trackRepository;

    @Inject
    public TrackDAO() {
        this.trackRepository = new TrackRepository();
    }

    @Override
    public Tracks getTracksInPlaylist(int playlistId) throws TrackException {
        return trackRepository.getTracksInPlaylist(playlistId);
    }

    @Override
    public Tracks getAllTracksNotInPlaylist(int playlistId) throws TrackException {
        return trackRepository.getAllTracksNotInPlaylists(playlistId);
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) throws DeleteException {
        trackRepository.removeTrackFromPlaylist(playlistId, trackId);
    }

    @Override
    public void addTrackToPlaylist(int playlistId, int trackId) throws InsertionException {
        trackRepository.addTrackToPlaylist(playlistId, trackId);
    }

//	private Connection conn;
//	private final static Logger LOGGER = Logger.getLogger(TrackDAO.class.getName());
//
//	@Inject
//	public TrackDAO(IDatabaseConnection conn) throws Exception {
//		this.conn = conn.getConnection();
//	}
//
//	public Tracks getAllTracksNotInPlaylist(int playlistId) throws TrackException {
//		String query = "select * from tracks \n" +
//				"where id not in (\n" +
//				"select trackid \n" +
//				"from trackinplaylist \n" +
//				"where playlistid = ?)";
//
//		return executeGetTracksQueryByCustomQuery(query, playlistId);
//	}
//
//	public Tracks getTracksInPlaylist(int playlistId) throws TrackException {
//		String query = "select * from tracks \n" +
//				"where id in (\n" +
//				"select trackId \n" +
//				"from trackinplaylist \n" +
//				"where playlistId = ?)";
//
//		return executeGetTracksQueryByCustomQuery(query, playlistId);
//	}
//
//	public void removeTrackFromPlaylist(int playlistId, int trackId) throws DeleteException {
//		String query = "delete from trackinplaylist where trackId = ? AND playlistId = ?";
//
//		try (
//				PreparedStatement statement = conn.prepareStatement(query)
//		) {
//			statement.setInt(1, trackId);
//			statement.setInt(2, playlistId);
//
//			statement.execute();
//		} catch (Exception e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with removing a track from the playlist in the DB: " + e);
//			throw new DeleteException("Track " + trackId + "from playlist " + playlistId);
//		}
//	}
//
//	public void addTrackToPlaylist(int playlistId, Track dto) throws InsertionException {
//		String query = "insert into trackinplaylist values (?,?)";
//
//		try (
//				PreparedStatement statement = conn.prepareStatement(query)
//		) {
//			statement.setInt(1, playlistId);
//			statement.setInt(2, dto.getId());
//			statement.execute();
//			updateTrackAvailability(dto.isOfflineAvailable(), dto.getId());
//
//		} catch (Exception e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with adding a track to the playlist in the DB: " + e);
//			throw new InsertionException("Track " + dto.getId() + " into playlist " + playlistId);
//		}
//	}
//
//	private void updateTrackAvailability(boolean availability, int trackId) throws UpdateException {
//		String query = "update tracks set offlineAvailable = ? where id = ?";
//
//		try (
//				PreparedStatement statement = conn.prepareStatement(query)
//		) {
//			statement.setBoolean(1, availability);
//			statement.setInt(2, trackId);
//
//			statement.execute();
//		} catch (Exception e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with updating the trackavailability in the playlist: " + e);
//			throw new UpdateException("Track with id:" + trackId);
//		}
//	}
//
//	private Tracks executeGetTracksQueryByCustomQuery(String query, int playlistId) throws TrackException {
//		List<Track> tracks = new ArrayList<>();
//		try (
//				PreparedStatement statement = conn.prepareStatement(query)
//		) {
//			statement.setInt(1, playlistId);
//
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				tracks.add(new Track(
//						rs.getInt("id"),
//						rs.getString("title"),
//						rs.getString("performer"),
//						rs.getInt("duration"),
//						rs.getString("albumTitle"),
//						rs.getInt("playcount"),
//						rs.getString("publicationDate"),
//						rs.getString("description"),
//						rs.getBoolean("offlineAvailable")
//				));
//			}
//
//			return new Tracks(tracks);
//		} catch (Exception e) {
//			LOGGER.log(Level.SEVERE, "Something went wrong with getting the tracks from the DB: " + e);
//			throw new TrackException("Something went wrong with getting the tracks from the DB");
//		}
//	}
}
