package com.directory.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Property entity.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Entity(name = "properties")
public class Property {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

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
}
