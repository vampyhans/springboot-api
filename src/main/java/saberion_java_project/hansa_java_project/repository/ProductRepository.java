package saberion_java_project.hansa_java_project.repository;

import saberion_java_project.hansa_java_project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be added here if needed
}
