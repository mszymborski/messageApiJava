package pl.message.api.rest.message.impl;

import pl.message.api.rest.user.impl.UserDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MessageDTO implements Serializable {

    private Long id;
    private String title;
    private String content;
    private UserDTO sender;
    private List<UserDTO> recipients;
    private MessageStatus status;
    private LocalDate created;
    private LocalDate lastModified;

    public MessageDTO() {
    }

    public MessageDTO(String title, String content, UserDTO sender, List<UserDTO> recipients) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
    }

    public MessageDTO(Long id, String title, String content, UserDTO sender, List<UserDTO> recipients, MessageStatus status, LocalDate created, LocalDate lastModified) {
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

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public List<UserDTO> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<UserDTO> recipients) {
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

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", recipients=" + recipients +
                ", status=" + status +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }
}
