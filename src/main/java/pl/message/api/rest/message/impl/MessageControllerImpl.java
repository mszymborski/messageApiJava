package pl.message.api.rest.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.message.interfaces.MessageController;
import pl.message.api.rest.message.interfaces.MessageSend;
import pl.message.api.rest.message.interfaces.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MessageControllerImpl implements MessageController, CUDController<MessageDTO>, MessageSend {

    @Autowired
    MessageService messageService;

    @Override
    @PostMapping("/api/message/")
    public ResponseEntity<?> create(@Valid @RequestBody MessageDTO messageDTO) {
        MessageDTO message = messageService.createMessage(messageDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PutMapping("/api/message/")
    public ResponseEntity<?> update(@RequestParam("id") Long messageId, @Valid @RequestBody MessageDTO messageDTO) {
        MessageDTO message = messageService.updateMessage(messageId, messageDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/api/message/")
    public ResponseEntity<?> delete(@RequestParam("id")Long messageId) {
        messageService.deleteMessage(messageId);
        return new ResponseEntity<>("Successfully delete message: " + messageId, HttpStatus.OK);
    }

    @Override
    @GetMapping("/api/send/")
    public ResponseEntity<?> send(@RequestParam("id")Long id) {
        return null;
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessages() {
        List<MessageDTO> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessagesByTitle(@RequestParam("title") String title) {
        List<MessageDTO> messages = messageService.getAllMessagesByTitle(title);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessagesBySender(@RequestParam("sender") String sender) {
        List<MessageDTO> messages = messageService.getAllMessagesBySender(sender);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
