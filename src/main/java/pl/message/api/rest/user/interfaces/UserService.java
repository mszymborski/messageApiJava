package pl.message.api.rest.user.interfaces;

import pl.message.api.rest.exceptions.EmailDuplicateException;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.user.impl.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO) throws EmailDuplicateException;
    UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundUserException, EmailDuplicateException;
    List<UserDTO> getAllUsers() throws NotFoundUserException;
    UserDTO getByEmail(String email) throws NotFoundUserException;
    List<UserDTO> getByName(String name) throws NotFoundUserException;
    void deleteUser(Long userId) throws NotFoundUserException;
}
