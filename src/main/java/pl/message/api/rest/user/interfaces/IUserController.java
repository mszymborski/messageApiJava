package pl.message.api.rest.user.interfaces;

import org.springframework.http.ResponseEntity;

public interface IUserController {

    ResponseEntity<?> getUsers();
    ResponseEntity<?> getUser(String name, String email);

}
