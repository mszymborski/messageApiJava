package pl.message.api.rest.message.impl;

import pl.message.api.rest.user.impl.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private MessageStatus status;
    private LocalDate created;
    @Column(name = "last_modified")
    private LocalDate lastModified;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "senderId", nullable = false)
    private User sender;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipient_assoc",
        joinColumns = { @JoinColumn(name = "messageId") },
        inverseJoinColumns = { @JoinColumn(name = "userId") } )
    private List<User> recipients;

    public Message() {
    }

    public Message(String title, String content, User sender, List<User> recipients) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
    }

    public Message(Long id, String title, String content, User sender, List<User> recipients) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
        this.status = MessageStatus.CREATED;
        this.created = LocalDate.now();
        this.lastModified = LocalDate.now();
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
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
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getId() == message.getId() &&
                Objects.equals(getTitle(), message.getTitle()) &&
                Objects.equals(getContent(), message.getContent()) &&
                Objects.equals(getSender(), message.getSender()) &&
                Objects.equals(getRecipients(), message.getRecipients());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getContent(), getSender(), getRecipients());
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sender=" + sender +
                ", recipients=" + recipients +
                '}';
    }
}