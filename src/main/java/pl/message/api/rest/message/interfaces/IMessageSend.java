package pl.message.api.rest.message.interfaces;

import org.springframework.http.ResponseEntity;

public interface IMessageSend {
    ResponseEntity<?> send(Long id);
}
