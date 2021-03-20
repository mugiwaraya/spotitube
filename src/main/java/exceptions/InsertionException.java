package exceptions;

public class InsertionException extends Throwable {
	public InsertionException(String itemToInsert) {
		super("There was an error attempting to insert into database: " + itemToInsert);
	}
}
