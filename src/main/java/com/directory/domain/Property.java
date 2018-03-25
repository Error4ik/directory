package com.directory.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Objects;

/**
 * Property entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Property)) {
            return false;
        }
        Property property = (Property) o;
        return getId() == property.getId()
                && Objects.equals(getName(), property.getName())
                && Objects.equals(getValue(), property.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getValue());
    }

    @Override
    public String toString() {
        return String.format("Property: {id=%s name=%s value=%s %s}",
                this.getId(), this.getName(), this.getValue(), this.getModel().toString());
    }
}
