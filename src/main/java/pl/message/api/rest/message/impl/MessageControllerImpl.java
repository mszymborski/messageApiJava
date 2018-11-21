package pl.message.api.rest.message.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.exceptions.NotFoundMessageException;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.message.interfaces.*;
import pl.message.api.rest.util.MessagesUtil;

import javax.validation.constraints.Email;
import java.util.LinkedList;
import java.util.List;

@RestController("/api/message")
public class MessageControllerImpl implements MessageController, CUDController<MessageDTO>, MessageSend {

    private static final Logger logger = LoggerFactory.getLogger(MessageControllerImpl.class);

    @Autowired
    MessageService messageService;
    @Autowired
    MessageValidator messageValidator;
    @Autowired
    SendService sendService;
    @Autowired
    MessagesUtil messagesUtil;

    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MessageDTO messageDTO) {
        List<String> modelValidationErrors = new LinkedList<>();
        if(!messageValidator.validateCreationMessage(messageDTO, modelValidationErrors)){
            return new ResponseEntity<>("Bad request. Incorrect value in: " + modelValidationErrors, HttpStatus.BAD_REQUEST);
        }
        logger.debug(messagesUtil.getMessage("message.request"), messageDTO);
        try {
            MessageDTO message = messageService.createMessage(messageDTO);
            logger.debug(messagesUtil.getMessage("message.response"), message);
            logger.info("Create new message with id: {}", message.getId());
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long messageId, @RequestBody MessageDTO messageDTO) {
        try {
            logger.debug(messagesUtil.getMessage("message.id.request"), messageId, messageDTO);
            MessageDTO message = messageService.updateMessage(messageId, messageDTO);
            logger.debug(messagesUtil.getMessage("message.response"), message);
            logger.info("Successfully update message with id: {}", messageId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            logger.warn("Not found message with id: {}", messageId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NotFoundUserException e) {
            logger.warn("Not found user exception", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long messageId) {
        try {
            logger.debug(messagesUtil.getMessage("message.id"), messageId);
            messageService.deleteMessage(messageId);
            logger.info("Successfully delete message with id: {}", messageId);
            return new ResponseEntity<>("Successfully delete message with id: " + messageId, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            logger.warn("Not found message with id: {}", messageId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> send(@PathVariable("id")Long messageId) {
        try {
            sendService.sendMessage(messageId);
        } catch (NotFoundMessageException e) {
            logger.warn("Not found message with id: ", messageId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getMessages() {
        try {
            List<MessageDTO> messages = messageService.getAllMessages();
            logger.info("Return all messages");
            logger.debug(messagesUtil.getMessage("message.response"), messages);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            logger.warn("Not found any message", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @GetMapping(params = "title")
    public ResponseEntity<?> getMessagesByTitle(@RequestParam("title") String title) {
        try {
            logger.debug("Title: {}", title);
            List<MessageDTO> messages = messageService.getAllMessagesByTitle(title);
            logger.info("Return all messages with title: {}", title);
            logger.debug(messagesUtil.getMessage("message.response"), messages);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            logger.warn("Not found message with title: {}", title, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }  catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @GetMapping(params = "sender")
    public ResponseEntity<?> getMessagesBySender(@Email @RequestParam("sender") String sender) {
        try {
            logger.debug("Sender: {}", sender);
            List<MessageDTO> messages = messageService.getAllMessagesBySender(sender);
            logger.info("Return all messages for sender: {}", sender);
            logger.debug(messagesUtil.getMessage("message.response"), messages);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (NotFoundMessageException e) {
            logger.warn("Not found message for sender: {}", sender, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NotFoundUserException e) {
            logger.warn("Not found sender: {}", sender, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            logger.error(messagesUtil.getMessage("message.database.error"), e);
            return new ResponseEntity<>(messagesUtil.getMessage("message.response.database.error.response"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
