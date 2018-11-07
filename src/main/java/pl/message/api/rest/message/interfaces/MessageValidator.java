package pl.message.api.rest.message.interfaces;

import pl.message.api.rest.message.impl.MessageDTO;

import java.util.List;

public interface MessageValidator {
    boolean validateCreationMessage(MessageDTO messageDTO, List<String> modelValidationMap);
}
