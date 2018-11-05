package pl.message.api.rest.message.interfaces;

import pl.message.api.rest.message.impl.Message;

public interface SendService {

    void sendMessage(Message message);

}
