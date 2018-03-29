package com.directory.repository;

import com.directory.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Property repository.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
public interface PropertyRepository extends JpaRepository<Property, UUID> {
    Property findById(final UUID id);
    List<Property> findAllByModelIdOrderByName(final UUID id);
}
