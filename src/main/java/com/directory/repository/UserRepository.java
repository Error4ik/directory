package com.directory.repository;

import com.directory.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * User repository.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(final String email);
    User findById(final UUID id);
}
