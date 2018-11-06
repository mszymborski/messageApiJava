package pl.message.api.rest.exceptions;

public class NotFoundUserException extends Exception {

	private Long id;

	public NotFoundUserException(Long id) {
		super("NotFoundUserException: Not found user with id = " + id);
	}
}
