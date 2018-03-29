package com.directory.service;

import com.directory.domain.DirectoryAttribute;
import com.directory.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Attribute service.
 *
 * @author Alexey Voronin.
 * @since 28.03.2018.
 */
@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    public void deleteAttribute(final UUID id) {
        this.attributeRepository.delete(id);
    }

    public DirectoryAttribute save(final DirectoryAttribute attribute) {
        return this.attributeRepository.save(attribute);
    }

    public DirectoryAttribute findAttributeById(final UUID id) {
        return this.attributeRepository.findById(id);
    }
}
