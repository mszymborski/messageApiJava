package pl.message.api.rest.message.interfaces;

import org.springframework.http.ResponseEntity;

public interface MessageController {

    ResponseEntity<?> getMessages();
    ResponseEntity<?> getMessagesByTitle(String title);
    ResponseEntity<?> getMessagesBySender(String email);

}
