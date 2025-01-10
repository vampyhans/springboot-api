package saberion_java_project.hansa_java_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saberion_java_project.hansa_java_project.entity.Attribute;
import saberion_java_project.hansa_java_project.repository.AttributeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    @Autowired
    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public Attribute saveAttribute(Attribute attribute) {
        return attributeRepository.save(attribute); // Save or update attribute
    }

    public Optional<Attribute> findAttributeById(Long id) {
        return attributeRepository.findById(id); // Fetch attribute by ID
    }

    public List<Attribute> findAllAttributes() {
        return attributeRepository.findAll(); // Fetch all attributes
    }

    public void deleteAttribute(Long id) {
        attributeRepository.deleteById(id); // Deletes the attribute
    }
}
