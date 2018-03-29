package com.directory.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Model entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "models")
public class Model {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "dir_id")
    private Directory directory;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "model_id")
    @OrderBy("name")
    private List<Property> properties;

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

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }
        Model model = (Model) o;
        return getId() == model.getId()
                && Objects.equals(getName(), model.getName())
                && Objects.equals(getDirectory(), model.getDirectory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDirectory());
    }
}
