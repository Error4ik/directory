package com.directory.repository;

import com.directory.domain.DirectoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Attribute repository.
 *
 * @author Alexey Voronin.
 * @since 28.03.2018.
 */
@Repository
public interface AttributeRepository extends JpaRepository<DirectoryAttribute, UUID> {
    DirectoryAttribute findById(final UUID id);
}
