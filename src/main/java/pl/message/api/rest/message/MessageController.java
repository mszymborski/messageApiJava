package pl.message.api.rest.message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.message.api.rest.interfaces.ICUDController;
import pl.message.api.rest.message.interfaces.IMessageController;
import pl.message.api.rest.message.interfaces.IMessageSend;

@RestController
public class MessageController implements IMessageController, ICUDController<MessageDTO>, IMessageSend {

    @Override
    @PostMapping("/api/message/")
    public ResponseEntity<?> create(@RequestBody MessageDTO message) {
        return null;
    }

    @Override
    @PutMapping("/api/message/")
    public ResponseEntity<?> update(@RequestParam("id") Long messageId, @RequestBody MessageDTO model) {
        return null;
    }

    @Override
    @DeleteMapping("/api/message/")
    public ResponseEntity<?> delete(@RequestParam("id")Long messageId) {
        return null;
    }

    @Override
    @GetMapping("/api/send/")
    public ResponseEntity<?> send(@RequestParam("id")Long id) {
        return null;
    }

    @Override
    @GetMapping("/api/messages/")
    public ResponseEntity<?> getMessages() {
        return null;
    }

    @Override
    @GetMapping("/api/message/")
    public ResponseEntity<?> getMessage(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "sender", required = false) String sender) {
        return null;
    }
}
