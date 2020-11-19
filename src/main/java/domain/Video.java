package domain;

public class Video extends Track {
    private String publicationDate;
    private String description;

    public Video(int id, String title, String performer, String URL, int duration, Boolean offlineAvailable, String publicationDate, String description) {
        super(id, title, performer, URL, duration, offlineAvailable);
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
