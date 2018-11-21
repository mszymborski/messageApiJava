package pl.message.api.rest.message.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pl.message.api.rest.message.interfaces.MessageValidator;

import java.util.List;

@Component
public class MessageValidatorImpl implements MessageValidator {

    private static final Logger logger = LoggerFactory.getLogger(MessageValidatorImpl.class);

    @Override
    public boolean validateCreationMessage(MessageDTO messageDTO, List<String> modelValidationErrors) {
        boolean valid = true;
        if(messageDTO.getSender() == null && StringUtils.isEmpty(messageDTO.getSender().getEmail())){
            valid = false;
            modelValidationErrors.add("sender");
        }
        if(CollectionUtils.isEmpty(messageDTO.getRecipients())) {
            valid = false;
            modelValidationErrors.add("recipients");
        }
        if(StringUtils.isEmpty(messageDTO.getTitle())){
            valid = false;
            modelValidationErrors.add("title");
        }
        if (StringUtils.isEmpty(messageDTO.getContent())) {
            valid = false;
            modelValidationErrors.add("content");
        }
        if(!valid){
            logger.warn("Incorrect request: %s Incorrect value in: %s", messageDTO, modelValidationErrors);
        }
        return valid;
    }
}
