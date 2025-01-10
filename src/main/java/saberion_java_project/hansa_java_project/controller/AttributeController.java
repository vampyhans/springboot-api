package saberion_java_project.hansa_java_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saberion_java_project.hansa_java_project.entity.Attribute;
import saberion_java_project.hansa_java_project.service.AttributeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    private final AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute) {
        Attribute savedAttribute = attributeService.saveAttribute(attribute);
        return ResponseEntity.ok(savedAttribute);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Attribute>> getAttributeById(@PathVariable Long id) {
        Optional<Attribute> attribute = attributeService.findAttributeById(id);
        return ResponseEntity.ok(attribute);
    }

    @GetMapping
    public ResponseEntity<List<Attribute>> getAllAttributes() {
        List<Attribute> attributes = attributeService.findAllAttributes();
        return ResponseEntity.ok(attributes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
        attributeService.deleteAttribute(id);
        return ResponseEntity.noContent().build();
    }
}
