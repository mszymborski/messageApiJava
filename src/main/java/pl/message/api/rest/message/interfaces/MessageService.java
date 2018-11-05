package pl.message.api.rest.message.interfaces;

import pl.message.api.rest.message.impl.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO createMessage(MessageDTO messageDTO);
    MessageDTO updateMessage(Long id, MessageDTO messageDTO);
    void deleteMessage(Long id);
    List<MessageDTO> getAllMessages();
    List<MessageDTO> getAllMessagesByTitle(String messageTitle);
    List<MessageDTO> getAllMessagesBySender(String senderEmail);

}
