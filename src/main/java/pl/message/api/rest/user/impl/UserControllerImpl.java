package pl.message.api.rest.user.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.interfaces.CUDController;
import pl.message.api.rest.user.interfaces.UserController;

@RestController
public class UserControllerImpl implements CUDController<UserDTO>, UserController {

    @GetMapping(value = "/api/users/")
    public ResponseEntity<?> getUsers(){
        return null;
    }

    @Override
    @GetMapping(value = "/api/users/")
    public ResponseEntity<?> getUsersByName(@RequestParam(value = "email") String name) {
        return null;
    }

    @GetMapping(value = "/api/user/")
    public ResponseEntity<?> getUser(@RequestParam(value = "email") String email){
        return null;
    }

    @Override
    @PostMapping(value = "api/user/")
    public ResponseEntity<?> create(@RequestBody UserDTO user){
        return null;
    }

    @Override
    @PutMapping(value = "/api/user/")
    public ResponseEntity<?> update(@RequestParam("id") Long userId, @RequestBody UserDTO user){
        return null;
    }

    @Override
    @DeleteMapping(value = "/api/user/")
    public ResponseEntity<?> delete(@RequestParam("id") Long userId){
        return null;
    }

}
