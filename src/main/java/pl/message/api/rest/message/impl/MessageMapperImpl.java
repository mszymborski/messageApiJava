package pl.message.api.rest.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.message.api.rest.interfaces.Mapper;
import pl.message.api.rest.user.impl.User;
import pl.message.api.rest.user.impl.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapperImpl implements Mapper<Message, MessageDTO> {

    private final Mapper<User, UserDTO> userMapper;

    @Autowired
    public MessageMapperImpl(Mapper<User, UserDTO> userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public MessageDTO getDTO(Message message) {
        MessageDTO dto = new MessageDTO(message.getId(), message.getTitle(), message.getContent(),
                userMapper.getDTO(message.getSender()), message.getRecipients().stream().map(userMapper::getDTO).collect(Collectors.toList()),
                message.getStatus(), message.getCreated(), message.getLastModified());
        return dto;
    }

    @Override
    public Message getEntity(MessageDTO dto){
        List<User> recipients = dto.getRecipients().stream().map(userMapper::getEntity).collect(Collectors.toList());
        User sender = userMapper.getEntity(dto.getSender());
        Message message = new Message(dto.getId(), dto.getTitle(), dto.getContent(), sender,
                recipients);
        return message;
    }


}
