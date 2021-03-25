package dao;

import dto.Track;
import dto.Tracks;
import exceptions.DeleteException;
import exceptions.InsertionException;
import exceptions.TrackException;
import exceptions.UpdateException;
import interfaces.ITrack;

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

    public Tracks getTracksInPlaylist(int playlistId) throws TrackException {
        String query = "select * from tracks \n" +
                "where id in (\n" +
                "select trackId \n" +
                "from trackinplaylist \n" +
                "where playlistId = ?)";

        return executeGetTracksQuery(query, playlistId);
    }

    public Tracks getAllTracksNotInPlaylist(int playlistId) throws TrackException {
        String query = "select * from tracks \n" +
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

    public void addTrackToPlaylist(int playlistId, Track dto) throws InsertionException {
        String query = "insert into trackinplaylist values (?,?)";

        try (
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, dto.getId());
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

    private Tracks executeGetTracksQuery(String query, int playlistId) throws TrackException {
        List<Track> tracks = new ArrayList<>();
        try (
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, playlistId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                tracks.add(new Track(
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

            return new Tracks(tracks);
        } catch (Exception e) {
            e.printStackTrace();

            throw new TrackException();
        }
    }
}
