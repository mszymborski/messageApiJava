package pl.message.api.rest.user.interfaces;

import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<?> getUsers();
    ResponseEntity<?> getUsersByName(String name);
    ResponseEntity<?> getUser(String email);

}
