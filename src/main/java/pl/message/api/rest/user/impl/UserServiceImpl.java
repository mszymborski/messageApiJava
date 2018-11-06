package pl.message.api.rest.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.interfaces.Mapper;
import pl.message.api.rest.user.interfaces.UserRepository;
import pl.message.api.rest.user.interfaces.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Mapper<User,UserDTO> mapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.getEntity(userDTO);
        user = userRepository.save(user);
        return mapper.getDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundUserException {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new NotFoundUserException(id));
        if(!StringUtils.isEmpty(userDTO.getName())){
            oldUser.setName(userDTO.getName());
        }
        if(!StringUtils.isEmpty(userDTO.getSurname())){
            oldUser.setSurname(userDTO.getSurname());
        }
        if(!StringUtils.isEmpty(userDTO.getEmail())){
            oldUser.setEmail(userDTO.getEmail());
        }
        oldUser.setLastModified(LocalDate.now());
        oldUser = userRepository.save(oldUser);
        return mapper.getDTO(oldUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::getDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        return mapper.getDTO(user);
    }

    @Override public List<UserDTO> getByName(String name) {
        List<User> users = userRepository.getALLByName(name);
        return users.stream().map(mapper::getDTO).collect(Collectors.toList());
    }
}
