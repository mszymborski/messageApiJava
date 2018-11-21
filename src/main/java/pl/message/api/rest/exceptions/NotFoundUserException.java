package pl.message.api.rest.exceptions;

import java.util.Map;

public class NotFoundUserException extends Exception {

    public NotFoundUserException() {
        super("Not found any user");
    }

    public NotFoundUserException(Map<String,String> parametersMap) {
		super("NotFoundUserException: Not found user for: " + parametersMap);
	}
}
