package pl.message.api.rest.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.exceptions.NotFoundMessageException;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.message.interfaces.MessageController;
import pl.message.api.rest.message.interfaces.MessageSend;
import pl.message.api.rest.message.interfaces.MessageService;
import pl.message.api.rest.message.interfaces.MessageValidator;

import java.util.LinkedList;
import java.util.List;

@RestController
public class MessageControllerImpl implements MessageController, CUDController<MessageDTO>, MessageSend {

    @Autowired
    MessageService messageService;
    @Autowired
    MessageValidator messageValidator;

    @Override
    @PostMapping("/api/message/")
    public ResponseEntity<?> create(@RequestBody MessageDTO messageDTO) {
        List<String> modelValidationError = new LinkedList<>();
        if(!messageValidator.validateCreationMessage(messageDTO, modelValidationError)){
            return new ResponseEntity<>("Bad request. Incorrect value in: " + modelValidationError, HttpStatus.BAD_REQUEST);
        }
        MessageDTO message = messageService.createMessage(messageDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PutMapping("/api/message/")
    public ResponseEntity<?> update(@RequestParam("id") Long messageId, @RequestBody MessageDTO messageDTO) {
        try {
            MessageDTO message = messageService.updateMessage(messageId, messageDTO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/api/message/")
    public ResponseEntity<?> delete(@RequestParam("id")Long messageId) {
        try {
            messageService.deleteMessage(messageId);
        } catch (NotFoundMessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
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
        try {
            List<MessageDTO> messages = messageService.getAllMessages();
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessagesByTitle(@RequestParam("title") String title) {
        try {
            List<MessageDTO> messages = messageService.getAllMessagesByTitle(title);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessagesBySender(@RequestParam("sender") String sender) {
        try {
            List<MessageDTO> messages = messageService.getAllMessagesBySender(sender);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
