package com.directory.service;

import com.directory.domain.Directory;
import com.directory.domain.DirectoryAttribute;
import com.directory.domain.User;
import com.directory.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Directory service.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepository directoryRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private AttributeService attributeService;

    public Directory save(final Directory directory) {
        String user = this.securityService.findLoggedUser();
        User u = this.userService.findUserByEmail(user);
        directory.setUser(u);
        return this.directoryRepository.save(directory);
    }

    public List<Directory> findAll() {
        return this.directoryRepository.findAll();
    }

    public void deleteById(final UUID id) {
        this.directoryRepository.delete(id);
    }

    public Directory findDirectoryById(final UUID id) {
        return this.directoryRepository.findById(id);
    }

    public List<Directory> findDirectoriesByUserId(final UUID id) {
        return this.directoryRepository.findAllByUserIdOrderByName(id);
    }

    public void updateDirectory(final UUID id, final String name, final String description) {
        Directory d = this.findDirectoryById(id);
        d.setName(name);
        d.setDescription(description);
        this.save(d);
    }

    public void prepareAndSave(final List<String> list) {
        final Directory directory = new Directory();
        directory.setName(list.remove(0));
        directory.setDescription(list.remove(0));
        this.save(directory);
        while (!list.isEmpty()) {
            final DirectoryAttribute attribute = new DirectoryAttribute();
            attribute.setName(list.remove(0));
            attribute.setDirectory(directory);
            this.attributeService.save(attribute);
        }
    }
}
