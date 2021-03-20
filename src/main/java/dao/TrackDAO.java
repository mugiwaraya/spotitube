package dao;

import dto.TrackDTO;
import dto.TracksDTO;
import exceptions.DeleteException;
import exceptions.TrackException;
import exceptions.UpdateException;
import interfaces.ITrack;
import exceptions.InsertionException;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements ITrack {

    private Connection conn;

    @Inject
    public TrackDAO(DatabaseConnection conn) {
        this.conn = conn.getConnection();
    }

    public TracksDTO getTracksInPlaylist(int playlistId) throws TrackException {
        String query = "select * from tracks \n" +
                "where id in (\n" +
                "select trackId \n" +
                "from trackinplaylist \n" +
                "where playlistId = ?)";

        return executeGetTracksQuery(query, playlistId);
    }

    public TracksDTO getAllTracksNotInPlaylist(int playlistId) throws TrackException {
        String query = "select * from track \n" +
                "where id not in (\n" +
                "select trackid \n" +
                "from trackinplaylist \n" +
                "where playlistid = ?)";

        return executeGetTracksQuery(query, playlistId);
    }

    public void removeTrackFromPlaylist(int playlistId, int trackId) throws DeleteException {
        String query = "delete from trackinplaylist where trackId = ? AND playlistId = ?";

        try (
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, trackId);
            statement.setInt(2, playlistId);

            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteException("Track " + trackId + "from playlist " + playlistId);
        }
    }

    public void addTrackToPlaylist(int playlistId, TrackDTO dto) throws InsertionException {
        String query = "insert into trackinplaylist values (?,?)";

        try (
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, dto.getId());
            statement.setInt(2, playlistId);
            statement.execute();
            updateTrackAvailability(dto.isOfflineAvailable(), dto.getId());

        } catch (Exception e){
            e.printStackTrace();
            throw new InsertionException("Track " + dto.getId() + " into playlist " + playlistId);
        }
    }

    private void updateTrackAvailability(boolean availability, int trackId) throws UpdateException {
        String query = "update tracks set offlineAvailable = ? where id = ?";

        try (
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setBoolean(1, availability);
            statement.setInt(2, trackId);

            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("Track with id:" + trackId);
        }
    }

    private TracksDTO executeGetTracksQuery(String query, int playlistId) throws TrackException {
        List<TrackDTO> tracks = new ArrayList<>();
        try (
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, playlistId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                tracks.add(new TrackDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("performer"),
                        rs.getInt("duration"),
                        rs.getString("albumTitle"),
                        rs.getInt("playcount"),
                        rs.getString("publicationDate"),
                        rs.getString("description"),
                        rs.getBoolean("offlineAvailable")
                ));
            }

            return new TracksDTO(tracks);
        } catch (Exception e) {
            e.printStackTrace();

            throw new TrackException();
        }
    }
}
