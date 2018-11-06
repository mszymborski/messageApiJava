package pl.message.api.rest.user.impl;

import org.springframework.stereotype.Component;
import pl.message.api.rest.interfaces.Mapper;

@Component
public class UserMapperImpl implements Mapper<User, UserDTO> {

    @Override
    public UserDTO getDTO(User entity) {
        UserDTO dto = new UserDTO(entity.getId(), entity.getName(), entity.getSurname(),entity.getEmail(), entity.getCreated(), entity.getLastModified());
        return dto;
    }

    @Override
    public User getEntity(UserDTO dto) {
        User user = new User(dto.getId(), dto.getName(), dto.getSurname(), dto.getEmail());
        return user;
    }
}
