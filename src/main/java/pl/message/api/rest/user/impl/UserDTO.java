package pl.message.api.rest.user.impl;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    @Email
    private String email;
    private LocalDate created;
    private LocalDate lastModified;

    public UserDTO() {
    }

    public UserDTO(String name, String surname, @Email String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UserDTO(UserDTO userDTO) {
        this(userDTO.name, userDTO.surname, userDTO.email);
    }

    public UserDTO(Long id, @NotNull String name, String surname, @Email @NotNull String email, LocalDate created, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.created = created;
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getName(), userDTO.getName()) &&
                Objects.equals(getSurname(), userDTO.getSurname()) &&
                Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname(), getEmail());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }
}
