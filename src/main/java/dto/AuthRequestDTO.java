package dto;

import javax.json.bind.annotation.JsonbProperty;

public class AuthRequestDTO {
    @JsonbProperty("user")
    private String username;
    @JsonbProperty("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

