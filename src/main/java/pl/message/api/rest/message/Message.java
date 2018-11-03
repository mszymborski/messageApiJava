package pl.message.api.rest.message;

import pl.message.api.rest.user.User;
import pl.message.api.rest.interfaces.IDtoCreator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "message")
public class Message implements IDtoCreator<MessageDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
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

    public Message(long id, String title, String content, User sender, List<User> recipients) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
    }

    public MessageDTO getDTO(){
        MessageDTO dto = new MessageDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setSender(sender.getEmail());
        dto.setRecipients(recipients.stream().map(u->u.getEmail()).collect((Collectors.toList())));
        return dto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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