package pl.message.api.rest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessagesUtil {

    private final MessageSource messageSource;

    @Autowired
    public MessagesUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String property){
        return messageSource.getMessage(property, null, Locale.getDefault());
    }

}
