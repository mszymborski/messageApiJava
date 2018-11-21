package pl.message.api.rest.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pl.message.api.rest.exceptions.EmailDuplicateException;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.interfaces.Mapper;
import pl.message.api.rest.user.interfaces.UserRepository;
import pl.message.api.rest.user.interfaces.UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper<User,UserDTO> mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Mapper<User, UserDTO> mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws DataAccessException, EmailDuplicateException {
        checkEmailDuplicate(userDTO.getEmail());
        User user = mapper.getEntity(userDTO);
        user = userRepository.save(user);
        return mapper.getDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundUserException, DataAccessException, EmailDuplicateException {
        User oldUser = getById(id);
        if(!StringUtils.isEmpty(userDTO.getName())){
            oldUser.setName(userDTO.getName());
        }
        if(!StringUtils.isEmpty(userDTO.getSurname())){
            oldUser.setSurname(userDTO.getSurname());
        }
        if(!StringUtils.isEmpty(userDTO.getEmail()) && !oldUser.getEmail().equals(userDTO.getEmail())){
            checkEmailDuplicate(userDTO.getEmail());
            oldUser.setEmail(userDTO.getEmail());
        }
        oldUser.setLastModified(LocalDate.now());
        oldUser = userRepository.save(oldUser);
        return mapper.getDTO(oldUser);
    }

    @Override
    public List<UserDTO> getAllUsers() throws NotFoundUserException, DataAccessException {
        List<User> users = userRepository.findAll();
        if(CollectionUtils.isEmpty(users)){
            throw new NotFoundUserException();
        }
        return users.stream().map(mapper::getDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getByEmail(String email) throws NotFoundUserException, DataAccessException {
        User user = userRepository.getByEmail(email)
                .orElseThrow(() ->{
                    Map<String, String> parameter = new HashMap<>();
                    parameter.put("email",String.valueOf(email));
                    return new NotFoundUserException(parameter);
                });
        return mapper.getDTO(user);
    }

    @Override
    public List<UserDTO> getByName(String name) throws NotFoundUserException, DataAccessException {
        List<User> users = userRepository.getALLByName(name);
        if(CollectionUtils.isEmpty(users)){
            Map<String,String> parameter = new HashMap<>();
            parameter.put("name", name);
            throw new NotFoundUserException(parameter);
        }
        return users.stream().map(mapper::getDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) throws NotFoundUserException {
        getById(id);
        userRepository.deleteById(id);
    }

    public User getById(Long id) throws NotFoundUserException, DataAccessException {
        User user = userRepository.findById(id)
                .orElseThrow(() ->{
                    Map<String, String> parameter = new HashMap<>();
                    parameter.put("id",String.valueOf(id));
                    return new NotFoundUserException(parameter);
                });
        return user;
    }

    private void checkEmailDuplicate(String email) throws EmailDuplicateException {
        if(userRepository.getByEmail(email).isPresent()){
            throw new EmailDuplicateException(email);
        }
    }
}
