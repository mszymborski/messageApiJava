package pl.message.api.rest.message.impl;

import pl.message.api.rest.user.impl.UserDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageDTO)) return false;
        MessageDTO dto = (MessageDTO) o;
        return Objects.equals(getId(), dto.getId()) &&
                Objects.equals(getTitle(), dto.getTitle()) &&
                Objects.equals(getContent(), dto.getContent()) &&
                Objects.equals(getSender(), dto.getSender()) &&
                Objects.equals(getRecipients(), dto.getRecipients()) &&
                getStatus() == dto.getStatus() &&
                Objects.equals(getCreated(), dto.getCreated()) &&
                Objects.equals(getLastModified(), dto.getLastModified());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getContent(), getSender(), getRecipients(), getStatus(), getCreated(), getLastModified());
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
