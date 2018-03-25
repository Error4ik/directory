package com.directory.service;

import com.directory.domain.Model;
import com.directory.domain.Property;
import com.directory.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Property save(final Property property){
        return this.propertyRepository.save(property);
    }

    public List<Property> getPropertiesByModelId(final int id) {
        return this.propertyRepository.findAllByModelId(id);
    }

    public void deleteProperty(final int id){
        this.propertyRepository.delete(id);
    }

    public Property findPropertyById(final int id) {
        return this.propertyRepository.findById(id);
    }

    public void prepareToSave(final List<String> list, final Model model) {
        List<Property> properties = new ArrayList<>();
        while (!list.isEmpty()) {
            Property property = new Property();
            property.setModel(model);
            property.setName(list.remove(0));
            property.setValue(list.remove(0));
            properties.add(property);
        }
        this.saveAll(properties);
    }

    private void saveAll(final List<Property> properties) {
        this.propertyRepository.save(properties);
    }
}
