package com.directory.service;

import com.directory.domain.Directory;
import com.directory.domain.User;
import com.directory.repository.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Directory save(final Directory directory) {
        String user = this.securityService.findLoggedUser();
        User u = this.userService.findUserByEmail(user);
        directory.setUser(u);
        return this.directoryRepository.save(directory);
    }

    public List<Directory> findAll() {
        return this.directoryRepository.findAll();
    }

    public void deleteById(final int id) {
        this.directoryRepository.delete(id);
    }

    public Directory findDirectoryById(final int id) {
        return this.directoryRepository.findById(id);
    }

    public List<Directory> findDirectoriesByUserId(final int id) {
        return this.directoryRepository.findAllByUserIdOrderByName(id);
    }
}
