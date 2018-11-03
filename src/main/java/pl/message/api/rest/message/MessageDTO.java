package pl.message.api.rest.message;

import java.io.Serializable;
import java.util.List;

public class MessageDTO implements Serializable {

    private String title;
    private String content;
    private String sender;
    private List<String> recipients;

    public MessageDTO() {
    }

    public MessageDTO(String title, String content, String sender, List<String> recipients) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
