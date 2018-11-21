package pl.message.api.rest.usermessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.message.api.rest.usermessage.interfaces.IUserMessagesController;

@RestController
public class UserMessagesController implements IUserMessagesController {

    @Override
    @GetMapping("/api/user/message/")
    public ResponseEntity<?> getUserMessages(@RequestParam("id") Long userId){
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    @GetMapping("/api/user/sent/")
    public ResponseEntity<?> getUserSentMessage(@RequestParam("id") Long userId){
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
