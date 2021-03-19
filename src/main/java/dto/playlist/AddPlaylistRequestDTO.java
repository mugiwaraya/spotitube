package dto.playlist;

import javax.json.bind.annotation.JsonbProperty;

public class AddPlaylistRequestDTO {
	@JsonbProperty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
