package pl.message.api.rest.user.impl;

import pl.message.api.rest.message.impl.Message;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String email;
    private LocalDate created;
    @Column(name = "last_modified")
    private LocalDate lastModified;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        mappedBy = "recipients")
    private List<Message> recipeMessage;

    public User() {
    }

    public User(Long id, String name, String surname, String email) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.created = LocalDate.now();
        this.lastModified = LocalDate.now();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Message> getRecipeMessage() {
        return recipeMessage;
    }

    public void setRecipeMessage(List<Message> recipeMessage) {
        this.recipeMessage = recipeMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getSurname(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", recipeMessage=" + recipeMessage +
                '}';
    }
}
