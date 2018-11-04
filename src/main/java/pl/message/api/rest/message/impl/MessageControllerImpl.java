package pl.message.api.rest.message.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.message.interfaces.MessageController;
import pl.message.api.rest.message.interfaces.MessageSend;

import javax.validation.Valid;

@RestController
public class MessageControllerImpl implements MessageController, CUDController<MessageDTO>, MessageSend {

    @Override
    @PostMapping("/api/message/")
    public ResponseEntity<?> create(@Valid @RequestBody MessageDTO message) {
        return null;
    }

    @Override
    @PutMapping("/api/message/")
    public ResponseEntity<?> update(@RequestParam("id") Long messageId, @Valid @RequestBody MessageDTO model) {
        return null;
    }

    @Override
    @DeleteMapping("/api/message/")
    public ResponseEntity<?> delete(@RequestParam("id")Long messageId) {
        return null;
    }

    @Override
    @GetMapping("/api/send/")
    public ResponseEntity<?> send(@RequestParam("id")Long id) {
        return null;
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessages() {
        return null;
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessageByTitle(@RequestParam("title") String title) {
        return null;
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessageBySender(@RequestParam("sender") String sender) {
        return null;
    }
}
