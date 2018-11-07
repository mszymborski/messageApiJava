package pl.message.api.rest.message.impl;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MessageDTO implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String sender;
    private List<String> recipients;
    private MessageStatus status;
    private LocalDate created;
    private LocalDate lastModified;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String title, String content, String sender, List<String> recipients, MessageStatus status, LocalDate created, LocalDate lastModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
        this.status = status;
        this.created = created;
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
}
