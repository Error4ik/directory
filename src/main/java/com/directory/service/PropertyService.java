package com.directory.service;

import com.directory.domain.Property;
import com.directory.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Property service.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property save(final Property property) {
        return this.propertyRepository.save(property);
    }

    public void deleteProperty(final UUID id) {
        this.propertyRepository.delete(id);
    }

    public Property findPropertyById(final UUID id) {
        return this.propertyRepository.findById(id);
    }
}
