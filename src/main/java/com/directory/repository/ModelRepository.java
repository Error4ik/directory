package com.directory.repository;

import com.directory.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Model repository.
 *
 * @author Alexey Voronin.
 * @since 23.03.2018.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {

    List<Model> findAllByDirectoryIdOrderByName(final int id);
}
