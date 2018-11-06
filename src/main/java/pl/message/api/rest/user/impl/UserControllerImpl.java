package pl.message.api.rest.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.exceptions.NotFoundUserException;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.user.interfaces.UserController;
import pl.message.api.rest.user.interfaces.UserService;

import java.util.List;

@RestController
public class UserControllerImpl implements CUDController<UserDTO>, UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/api/users/")
    public ResponseEntity<?> getUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/api/users/")
    public ResponseEntity<?> getUsersByName(@RequestParam(value = "name") String name) {
        List<UserDTO> users = userService.getByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/")
    public ResponseEntity<?> getUser(@RequestParam(value = "email") String email){
        UserDTO user = userService.getByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "api/user/")
    public ResponseEntity<?> create(@RequestBody UserDTO user){
        UserDTO newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @Override
    @PutMapping(value = "/api/user/")
    public ResponseEntity<?> update(@RequestParam("id") Long userId, @RequestBody UserDTO user){
        try {
            UserDTO udatUser = userService.updateUser(userId, user);
        } catch (NotFoundUserException e) {
            return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    @DeleteMapping(value = "/api/user/")
    public ResponseEntity<?> delete(@RequestParam("id") Long userId){
        return null;
    }

}
