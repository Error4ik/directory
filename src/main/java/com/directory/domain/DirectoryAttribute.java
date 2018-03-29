package com.directory.domain;

import javax.persistence.*;
import java.util.UUID;

/**
 * Directory attributes.
 *
 * @author Alexey Voronin.
 * @since 28.03.2018.
 */
@Entity(name = "attributes")
public class DirectoryAttribute {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
