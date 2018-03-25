package com.directory.repository;

import com.directory.domain.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Directory repository.
 *
 * @author Alexey Voronin.
 * @since 20.03.2018.
 */
@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Integer> {
    Directory findById(final int id);

    List<Directory> findAllByUserIdOrderByName(final int id);
}
