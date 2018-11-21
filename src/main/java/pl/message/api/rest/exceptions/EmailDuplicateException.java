package pl.message.api.rest.exceptions;

public class EmailDuplicateException extends Exception {

    public EmailDuplicateException(String email) {
        super("User with email " + email +" already exits");
    }
}
