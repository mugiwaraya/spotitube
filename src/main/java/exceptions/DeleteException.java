package exceptions;

public class DeleteException extends Throwable {
	public DeleteException(String itemToDelete) {
		super("There was an error attempting to delete from database: " + itemToDelete);
	}
}
