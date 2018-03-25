package com.directory.repository;

import com.directory.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Property repository.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Property findById(final int id);

    List<Property> findAllByModelId(final int id);
}
