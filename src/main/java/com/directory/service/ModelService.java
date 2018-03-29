package com.directory.service;

import com.directory.domain.Directory;
import com.directory.domain.Model;
import com.directory.domain.Property;
import com.directory.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Model service.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private PropertyService propertyService;

    public Model save(final Model model) {
        return this.modelRepository.save(model);
    }

    public void prepareAndSave(final UUID id, final List<String> list) {
        final Directory directory = this.directoryService.findDirectoryById(id);
        final Model model = new Model();
        model.setDirectory(directory);
        model.setName(list.remove(0));
        this.save(model);
        while (!list.isEmpty()) {
            final Property property = new Property();
            property.setModel(model);
            property.setName(list.remove(0));
            property.setValue(list.remove(0));
            this.propertyService.save(property);
        }
    }

    public void deleteModelById(final UUID id) {
        this.modelRepository.delete(id);
    }

    public Model findModelById(final UUID id) {
        return this.modelRepository.findById(id);
    }

    public List<Model> getModels() {
        return this.modelRepository.findAll();
    }
}
