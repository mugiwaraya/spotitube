package domain;

public class Song extends Track {
    private String albumTitle;

    public Song(int id, String title, String performer, String URL, int duration, Boolean offlineAvailable, String albumTitle) {
        super(id, title, performer, URL, duration, offlineAvailable);
        if (albumTitle != null)
            this.albumTitle = albumTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbum(String albumTitle) {
        this.albumTitle = albumTitle;
    }
}
