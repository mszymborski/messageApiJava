package pl.message.api.rest.message.interfaces;

import pl.message.api.rest.exceptions.NotFoundMessageException;

public interface SendService {

    void sendMessage(Long messageId) throws NotFoundMessageException;

}
