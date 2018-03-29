package com.directory.repository;

import com.directory.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Model repository.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    Model findById(final UUID id);
    List<Model> findAllByDirectoryId(final UUID id);
}
