package pl.message.api.rest.exceptions;

import java.util.Map;


public class NotFoundMessageException extends Exception {

    public NotFoundMessageException() {
        super("Not found any messages.");
    }

    public NotFoundMessageException(Map<String,String> parametersMap){
        super("Not found messages for: " + parametersMap);
    }
}
