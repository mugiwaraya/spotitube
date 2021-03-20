package exceptions;

public class TokenSavingFailedException extends Throwable {
	public TokenSavingFailedException() {
		super("Failed to save token.");
	}
}
