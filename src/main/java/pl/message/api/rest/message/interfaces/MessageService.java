package pl.message.api.rest.message.interfaces;

import org.springframework.dao.DataAccessException;
import pl.message.api.rest.exceptions.NotFoundMessageException;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.message.impl.Message;
import pl.message.api.rest.message.impl.MessageDTO;

import java.util.List;

public interface MessageService {

    MessageDTO createMessage(MessageDTO messageDTO) throws DataAccessException;
    MessageDTO updateMessage(Long id, MessageDTO messageDTO) throws NotFoundMessageException, NotFoundUserException, DataAccessException;
    void deleteMessage(Long id) throws NotFoundMessageException, DataAccessException;
    List<MessageDTO> getAllMessages() throws NotFoundMessageException, DataAccessException;
    List<MessageDTO> getAllMessagesByTitle(String messageTitle) throws NotFoundMessageException, DataAccessException;
    List<MessageDTO> getAllMessagesBySender(String senderEmail) throws NotFoundMessageException, NotFoundUserException, DataAccessException;
    Message getByID(Long id) throws NotFoundMessageException, DataAccessException;

}
