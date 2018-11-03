package pl.message.api.rest.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.interfaces.ICUDController;
import pl.message.api.rest.user.interfaces.IUserController;

@RestController
public class UserController implements ICUDController<UserDTO>, IUserController {

    @GetMapping(value = "/api/users/")
    public ResponseEntity<?> getUsers(){
        return null;
    }

    @GetMapping(value = "/api/user/")
    public ResponseEntity<?> getUser(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "email", required = false) String email){
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
