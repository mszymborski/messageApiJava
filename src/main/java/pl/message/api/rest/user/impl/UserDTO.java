package pl.message.api.rest.user.impl;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
}
