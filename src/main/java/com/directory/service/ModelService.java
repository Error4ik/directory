package com.directory.service;

import com.directory.domain.Directory;
import com.directory.domain.Model;
import com.directory.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Model save(final Model model) {
        return this.modelRepository.save(model);
    }

    public List<Model> findAllByDirectoryId(final int id) {
        return this.modelRepository.findAllByDirectoryIdOrderByName(id);
    }

    public Model prepareToSave(final List<String> list) {
        Directory directory = this.directoryService.findDirectoryById(Integer.parseInt(list.remove(0)));
        Model model = new Model();
        model.setDirectory(directory);
        model.setName(list.remove(0));
        return model;
    }

    public void deleteModelById(final int id) {
        this.modelRepository.delete(id);
    }

    public Model findModelById(final int id) {
        return this.modelRepository.findOne(id);
    }
}
