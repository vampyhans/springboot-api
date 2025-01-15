package saberion_java_project.hansa_java_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saberion_java_project.hansa_java_project.dto.ProductDTO;
import saberion_java_project.hansa_java_project.entity.Product;
import saberion_java_project.hansa_java_project.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create products
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product savedProduct = productService.saveProduct(productDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')") // Both roles can read products
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findProductById(id);
        return product.isPresent()
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')") // Both roles can view products
    public ResponseEntity<List<Product>> getAllProducts() {
        System.out.println("Register API hit with data: " + userDto);
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete products
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
