package pl.message.api.rest.message.interfaces;

import org.springframework.http.ResponseEntity;

public interface MessageSend {
    ResponseEntity<?> send(Long id);
}
