package pl.message.api.rest.message.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.message.api.rest.exceptions.NotFoundMessageException;
import pl.message.api.rest.message.interfaces.MessageService;
import pl.message.api.rest.message.interfaces.SendService;

@Service
public class SendServiceImpl implements SendService {

    private final MessageService messageService;

    @Autowired
    public SendServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void sendMessage(Long messageId) throws NotFoundMessageException {
        Message message = messageService.getByID(messageId);

    }

}
