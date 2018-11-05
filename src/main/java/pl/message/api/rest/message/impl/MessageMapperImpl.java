package pl.message.api.rest.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.message.api.rest.interfaces.Mapper;
import pl.message.api.rest.user.impl.User;
import pl.message.api.rest.user.interfaces.UserRepository;

import java.util.stream.Collectors;

@Component
public class MessageMapperImpl implements Mapper<Message,MessageDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageDTO getDTO(Message message) {
        MessageDTO dto = new MessageDTO(message.getId(), message.getTitle(), message.getContent(),
                message.getSender().getEmail(), message.getRecipients().stream().map(User::getEmail).collect(Collectors.toList()),
                message.getStatus(), message.getCreated(), message.getLastModified());
        return dto;
    }

    @Override
    public Message getEntity(MessageDTO dto) {
        Message message = new Message(dto.getId(), dto.getTitle(), dto.getContent(), userRepository.getByEmail(dto.getSender()),
                dto.getRecipients().stream().map(userRepository::getByEmail).collect(Collectors.toList()));
        return message;
    }


}
