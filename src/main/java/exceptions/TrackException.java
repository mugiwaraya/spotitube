package exceptions;

public class TrackException extends Exception {

	public TrackException(String message) {
		super("There has been an error getting tracks." + message);
	}
}
