package exceptions;

public class UserNotAuthorizedException extends Throwable {
	public UserNotAuthorizedException(String itemToUpdate) {
		super("There was an error attempting to update in database database: ");
	}
}
