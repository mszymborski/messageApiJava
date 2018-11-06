package pl.message.api.rest.user.interfaces;

import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.user.impl.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundUserException;
    List<UserDTO> getAllUsers();
    UserDTO getByEmail(String email);
    List<UserDTO> getByName(String name);
}
