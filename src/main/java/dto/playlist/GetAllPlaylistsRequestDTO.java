package dto.playlist;

import javax.json.bind.annotation.JsonbProperty;

public class GetAllPlaylistsRequestDTO {
    @JsonbProperty
    private String token;

    public GetAllPlaylistsRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
