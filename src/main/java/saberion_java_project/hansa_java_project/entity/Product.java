package saberion_java_project.hansa_java_project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data // Lombok annotation to generate getters, setters, toString, equals, hashCode, and constructor
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates an all-args constructor
@Builder // Provides a builder pattern for the class
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 12)
    private String code;

    @Column(nullable = false, length = 40)
    private String category;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 400)
    private String description;

    @Column(name = "selling_price", nullable = false, precision = 13, scale = 2)
    private BigDecimal sellingPrice;

    @Column(name = "special_price", precision = 13, scale = 2)
    private BigDecimal specialPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private Status status = Status.DRAFT;

    @Column(name = "is_delivery_available", nullable = false)
    private Boolean isDeliveryAvailable;

    @Column(nullable = false, length = 100)
    private String image;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public enum Status {
        DRAFT, PUBLISHED, OUT_OF_STOCK
    }
}
