package com.directory.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Objects;

/**
 * Directory entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "directories")
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "directory_id")
    private List<Model> models;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Directory)) {
            return false;
        }
        Directory directory = (Directory) o;
        return getId() == directory.getId()
                && Objects.equals(getName(), directory.getName())
                && Objects.equals(getDescription(), directory.getDescription())
                && Objects.equals(getUser(), directory.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getUser());
    }

    @Override
    public String toString() {
        return String.format("Directory: {id=%s name=%s desc=%s}", this.getId(), this.getName(), this.getDescription());
    }
}
