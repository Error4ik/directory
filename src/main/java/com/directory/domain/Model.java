package com.directory.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.List;
import java.util.Objects;

/**
 * Model entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "model_id")
    private List<Property> properties;

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

    @Override
    public String toString() {
        return String.format("Model: {id=%s name=%s %s}", this.getId(), this.getName(), this.getDirectory().toString());
    }
}
