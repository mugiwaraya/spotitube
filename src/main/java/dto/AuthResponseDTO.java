package dto;

import javax.json.bind.annotation.JsonbProperty;

public class AuthResponseDTO {

    @JsonbProperty("user")
    private String username;
    @JsonbProperty("token")
    private String token;

    public AuthResponseDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

}
