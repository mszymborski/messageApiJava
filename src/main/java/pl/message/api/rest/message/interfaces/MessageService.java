package pl.message.api.rest.message.interfaces;

import pl.message.api.rest.exceptions.NotFoundMessageException;
import pl.message.api.rest.message.impl.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO createMessage(MessageDTO messageDTO);
    MessageDTO updateMessage(Long id, MessageDTO messageDTO) throws NotFoundMessageException;
    void deleteMessage(Long id) throws NotFoundMessageException;
    List<MessageDTO> getAllMessages() throws NotFoundMessageException;
    List<MessageDTO> getAllMessagesByTitle(String messageTitle) throws NotFoundMessageException;
    List<MessageDTO> getAllMessagesBySender(String senderEmail) throws NotFoundMessageException;

}
