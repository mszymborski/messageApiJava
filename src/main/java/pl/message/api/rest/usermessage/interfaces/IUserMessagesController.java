package pl.message.api.rest.usermessage.interfaces;

import org.springframework.http.ResponseEntity;

public interface IUserMessagesController {

    ResponseEntity<?> getUserMessages(Long id);
    ResponseEntity<?> getUserSentMessage(Long id);

}
