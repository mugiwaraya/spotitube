package domain;

import java.net.URL;

public abstract class Track {
    private int id;
    private String title;
    private String performer;
    private String URL;
    private int duration;
    private Boolean offlineAvailable;

    public Track(int id, String title, String performer, String URL, int duration, Boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.URL = URL;
        this.duration = duration;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean isAvailableOffline() {
        return this.offlineAvailable;
    }
}
