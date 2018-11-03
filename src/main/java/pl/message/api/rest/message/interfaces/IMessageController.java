package pl.message.api.rest.message.interfaces;

import org.springframework.http.ResponseEntity;

public interface IMessageController {

    ResponseEntity<?> getMessages();
    ResponseEntity<?> getMessage(String title, String sender);

}
