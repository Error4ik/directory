package com.directory.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import java.util.Objects;
import java.util.Set;

/**
 * User entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Set<Directory> directories;

    public User() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(Set<Directory> directories) {
        this.directories = directories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId()
                && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    public String toString() {
        return String.format("User: {id=%s email=%s}", this.getId(), this.getEmail());
    }
}
