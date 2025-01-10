package saberion_java_project.hansa_java_project.repository;

import saberion_java_project.hansa_java_project.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    // Custom query methods can be added here if needed
}
