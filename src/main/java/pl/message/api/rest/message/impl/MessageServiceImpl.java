package pl.message.api.rest.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pl.message.api.rest.exceptions.NotFoundMessageException;
import pl.message.api.rest.interfaces.Mapper;
import pl.message.api.rest.message.interfaces.MessageRepository;
import pl.message.api.rest.message.interfaces.MessageService;
import pl.message.api.rest.user.impl.User;
import pl.message.api.rest.user.interfaces.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    Mapper<Message,MessageDTO> mapper;

    @Override
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = mapper.getEntity(messageDTO);
        message = messageRepository.save(message);
        return mapper.getDTO(message);
    }

    @Override
    public MessageDTO updateMessage(Long id, MessageDTO dto) throws NotFoundMessageException {
        Message message = getByID(id);
        if(!StringUtils.isEmpty(dto.getTitle())){
            message.setTitle(dto.getTitle());
        }
        if(!StringUtils.isEmpty(dto.getContent())){
            message.setContent(dto.getContent());
        }
        if(!StringUtils.isEmpty(dto.getSender())){
            User sender = userRepository.getByEmail(dto.getSender());
            message.setSender(sender);
        }
        if(!CollectionUtils.isEmpty(dto.getRecipients())){
            message.setRecipients(dto.getRecipients().stream()
                .map(r -> userRepository.getByEmail(r)).collect(Collectors.toList()));
        }
        message.setLastModified(LocalDate.now());
        message.setStatus(MessageStatus.UPDATED);
        message = messageRepository.save(message);
        return mapper.getDTO(message);
    }

    @Override
    public void deleteMessage(Long id) throws NotFoundMessageException {
        getByID(id);
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDTO> getAllMessages() throws NotFoundMessageException {
        List<Message> messages = messageRepository.findAll();
        if(!CollectionUtils.isEmpty(messages)){
            throw new NotFoundMessageException();
        }
        return messages.stream().map(m -> mapper.getDTO(m)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> getAllMessagesByTitle(String messageTitle) throws NotFoundMessageException {
        List<Message> messages = messageRepository.getByTitle(messageTitle);
        if(!CollectionUtils.isEmpty(messages)){
            Map<String,String> parameter = new HashMap<>();
            parameter.put("title", messageTitle);
            throw new NotFoundMessageException(parameter);
        }
        return messages.stream().map(m -> mapper.getDTO(m)).collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> getAllMessagesBySender(String senderEmail) throws NotFoundMessageException {
        User sender = userRepository.getByEmail(senderEmail);
        List<Message> messages = messageRepository.getBySender(sender);
        if(!CollectionUtils.isEmpty(messages)){
            Map<String,String> parameter = new HashMap<>();
            parameter.put("sender", senderEmail);
            throw new NotFoundMessageException(parameter);
        }
        return messages.stream().map(m -> mapper.getDTO(m)).collect(Collectors.toList());
    }

    private Message getByID(Long id) throws NotFoundMessageException {
        Message message = messageRepository.findById(id)
                .orElseThrow(()->{
            Map<String, String> parameter = new HashMap<>();
            parameter.put("id",String.valueOf(id));
            return new NotFoundMessageException(parameter);
        });
        return message;
    }

}
