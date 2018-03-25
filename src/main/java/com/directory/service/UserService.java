package com.directory.service;

import com.directory.domain.User;
import com.directory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User service.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SecurityService securityService;

    public User save(final User user) {
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void delete(final User user) {
        this.userRepository.delete(user);
    }

    public User findUserByEmail(final String email) {
        return this.userRepository.findByEmail(email);
    }

    public User findUserById(final int id) {
        return this.userRepository.findById(id);
    }

    public void regUser(final User user) {
        final String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        this.save(user);
        this.securityService.autoLogin(user.getEmail(), password);
    }
}
