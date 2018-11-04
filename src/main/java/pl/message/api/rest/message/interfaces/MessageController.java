package pl.message.api.rest.message.interfaces;

import org.springframework.http.ResponseEntity;

public interface MessageController {

    ResponseEntity<?> getMessages();
    ResponseEntity<?> getMessageByTitle(String title);
    ResponseEntity<?> getMessageBySender(String email);

}
