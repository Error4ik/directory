package com.directory.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Directory entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "directories")
public class Directory {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "directory_id")
    @OrderBy("name")
    private Set<DirectoryAttribute> attributes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "dir_id")
    @OrderBy("name")
    private List<Model> models;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
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

    public Set<DirectoryAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<DirectoryAttribute> attributes) {
        this.attributes = attributes;
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
}
