package saberion_java_project.hansa_java_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saberion_java_project.hansa_java_project.dto.ProductDTO;
import saberion_java_project.hansa_java_project.entity.Attribute;
import saberion_java_project.hansa_java_project.entity.Product;
import saberion_java_project.hansa_java_project.repository.AttributeRepository;
import saberion_java_project.hansa_java_project.repository.ProductRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, AttributeRepository attributeRepository) {
        this.productRepository = productRepository;
        this.attributeRepository = attributeRepository;
    }

    public Product saveProduct(ProductDTO productDTO) {
        // Save Base64 image to a file and get the path
        String imagePath = saveBase64Image(productDTO.getBase64Image());

        // Map DTO to entity and set image path
        Product product = mapToEntity(productDTO);
        product.setImage(imagePath);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // Save product to database
        Product savedProduct = productRepository.save(product);

        // Map and save attributes
        List<Attribute> attributes = productDTO.getAttributes().stream()
                .map(dto -> mapToAttribute(dto, savedProduct))
                .collect(Collectors.toList());
        attributeRepository.saveAll(attributes);

        return savedProduct;
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    private String saveBase64Image(String base64Image) {
        if (base64Image == null || base64Image.isEmpty()) {
            throw new IllegalArgumentException("Base64 image data is required.");
        }

        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            String fileName = "product_" + System.currentTimeMillis() + ".png";
            String filePath = "path/to/image/directory/" + fileName;

            File imageFile = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }

            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save Base64 image", e);
        }
    }

    private Product mapToEntity(ProductDTO productDTO) {
        return Product.builder()
                .code(productDTO.getCode())
                .category(productDTO.getCategory())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .sellingPrice(productDTO.getSellingPrice())
                .specialPrice(productDTO.getSpecialPrice())
                .status(Product.Status.valueOf(productDTO.getStatus()))
                .isDeliveryAvailable(productDTO.getIsDeliveryAvailable())
                .build();
    }

    private Attribute mapToAttribute(ProductDTO.AttributeDTO dto, Product product) {
        return Attribute.builder()
                .product(product)
                .name(dto.getName())
                .attributeValue(dto.getAttributeValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
